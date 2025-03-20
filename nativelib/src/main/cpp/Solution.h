//
// Created by 24502 on 2025/3/20.
//

#ifndef SOLUTION_H // 防止头文件重复包含
#define SOLUTION_H

#include <string>
#include <unordered_map>

class Solution {
private:
    // 声明静态常量哈希表（类内初始化需在 C++17 或更高版本支持）
    static const std::unordered_map<char, int> symbolValues;

public:
    int romanToInt(std::string s);
};

#endif // SOLUTION_H