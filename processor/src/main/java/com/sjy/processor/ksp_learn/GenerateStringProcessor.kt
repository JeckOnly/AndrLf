package com.sjy.processor.ksp_learn

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.processing.SymbolProcessorProvider
import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSPropertyDeclaration
import com.google.devtools.ksp.symbol.KSVisitorVoid
import com.google.devtools.ksp.symbol.Modifier
import com.google.devtools.ksp.validate
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec

/**
 * Created by JeckOnly on 2025/3/6
 * Describe:
 */
class GenerateStringProcessorProvider : SymbolProcessorProvider {
    override fun create(environment: SymbolProcessorEnvironment): SymbolProcessor {
        return GenerateStringProcessor(
            options = environment.options,
            logger = environment.logger,
            codeGenerator = environment.codeGenerator
        )
    }
}

class GenerateStringProcessor(
    options: Map<String, String>,
    val logger: KSPLogger,
    val codeGenerator: CodeGenerator
) : SymbolProcessor {
    override fun process(resolver: Resolver): List<KSAnnotated> {
        logger.warn("process执行")
        val symbols = resolver.getSymbolsWithAnnotation("com.sjy.processor.ksp_learn.GenerateToString")
            .filter { it.validate() }
        val list = symbols.toList()
        list.filter { it is KSClassDeclaration && it.validate() }
            .forEach {
                // log name
                logger.warn("symbol KSClassDeclaration: ${(it as KSClassDeclaration)} qualifiedName: ${it.qualifiedName?.asString()}")
                it.accept(
                    GenerateStringVisitor(logger = logger, codeGenerator = codeGenerator),
                    Unit
                )
            }

        return emptyList()

    }
}

class GenerateStringVisitor(
    private val logger: KSPLogger,
    private val codeGenerator: CodeGenerator
) : KSVisitorVoid() {

    private lateinit var className: String
    private lateinit var packageName: String
    private val properties: MutableList<String> = mutableListOf()


    override fun visitClassDeclaration(classDeclaration: KSClassDeclaration, data: Unit) {
        val qualifiedName = classDeclaration.qualifiedName?.asString()

        //1. 合法性检查
        if (!classDeclaration.isNormalClass()) {
            logger.error(
                "@GenerateToString cannot target non-data class $qualifiedName",
                classDeclaration
            )
            return
        }

        if (qualifiedName == null) {
            logger.error(
                "@GenerateToString must target classes with qualified names",
                classDeclaration
            )
            return
        }

        //2. 解析Class信息
        className = qualifiedName
        packageName = classDeclaration.packageName.asString()
        logger.warn("className: $className, packageName: $packageName")

        classDeclaration.getAllProperties()
            .forEach {
                it.accept(this, Unit)
            }


        //3. 代码生成
        generateToStringMethod(classDeclaration)

    }

    private fun generateToStringMethod(classDeclaration: KSClassDeclaration) {

        // 函数内容
        val classNameWithoutPackage = className.substringAfterLast('.')

        val toStringContent = """
        return "$classNameWithoutPackage(${properties.joinToString(", ") { "$it=\${$it}" }})"
        """.trimIndent()

        val fileName = "${classNameWithoutPackage}_to_string"

        // 生成代码文件
        val fileSpec = FileSpec.builder(packageName, fileName).addFunction(
            FunSpec.builder("toString2")
                .receiver(ClassName(packageName, className))
                .returns(String::class)
                .addCode(toStringContent)
                .build()
        ).build()

        logger.warn("生成代码文件: $packageName.$fileName")
        // 写入文件
        codeGenerator.createNewFile(
            dependencies = Dependencies(aggregating = true, sources = listOf(classDeclaration.containingFile!!).toTypedArray()),
            packageName = packageName,
            fileName = fileName
        ).use { outputStream ->
            outputStream.writer()
                .use {
                    fileSpec.writeTo(it)
                }
        }

    }

    override fun visitPropertyDeclaration(property: KSPropertyDeclaration, data: Unit) {
        properties.add(property.simpleName.asString())
    }
}

private fun KSClassDeclaration.isNormalClass(): Boolean {
    return !this.isAbstract() && !this.isSealed() && !this.isInner() && !this.isDataClass()
}

private fun KSClassDeclaration.isAbstract(): Boolean {
    return this.modifiers.contains(Modifier.ABSTRACT)
}

private fun KSClassDeclaration.isSealed(): Boolean {
    return this.modifiers.contains(Modifier.SEALED)
}

private fun KSClassDeclaration.isInner(): Boolean {
    return this.modifiers.contains(Modifier.INNER)
}

private fun KSClassDeclaration.isDataClass(): Boolean {
    return this.modifiers.contains(Modifier.DATA)
}
