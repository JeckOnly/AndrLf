//
// Created by 24502 on 2025/3/20.
//

#include "Solution.h" // 包含对应的头文件

// 定义并初始化静态成员（必须在类外显式定义）
const std::unordered_map<char, int> Solution::symbolValues = {
        {'I', 1},
        {'V', 5},
        {'X', 10},
        {'L', 50},
        {'C', 100},
        {'D', 500},
        {'M', 1000},
};

// 成员函数实现
int Solution::romanToInt(std::string s) {
    int ans = 0;
    int n = s.length();
    for (int i = 0; i < n; ++i) {
        int value = symbolValues.at(s[i]);
        if (i < n - 1 && value < symbolValues.at(s[i + 1])) {
            ans -= value;
        } else {
            ans += value;
        }
    }
    return ans;
}
