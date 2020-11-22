# jmtrace
Nanjing University, Computer Science's Course, Guidance to Software Engineering Research, 2019 Fall, Programming Homework II

# 编译说明

## Software Requirements

```bash
# jdk >= 1.8.0
```

## Build

```bash
$ sudo make
```

## Test Example

```bash
$ jmtrace -jar test/Test.jar Test
W 1 000000002f92e0f4 Test.a
Start...
W 1 0000000028a418fc Test.str
W 1 0000000028a418fc Test.str[0]
W 1 0000000028a418fc Test.str[1]
W 1 0000000028a418fc Test.strr
W 1 0000000028a418fc Test.strr[0]
W 1 0000000028a418fc Test.strr[1]
W 1 0000000028a418fc Test.strr[2]
W 1 0000000028a418fc Test.strr[3]
W 1 0000000028a418fc Test.strr[4]
W 1 0000000028a418fc Test.b
R 1 000000002f92e0f4 Test.a
R 1 0000000028a418fc Test.b
R 1 0000000028a418fc Test.b[1]
W 1 0000000028a418fc Test.b[1]
R 1 0000000028a418fc Test.b
R 1 000000002f92e0f4 Test.a
R 1 000000002f92e0f4 Test.a[2]
W 1 000000002f92e0f4 Test.a[2]
R 1 0000000028a418fc Test.str
R 1 0000000028a418fc Test.strr
R 1 0000000028a418fc Test.strr[1]
W 1 0000000028a418fc Test.strr[0]
R 1 0000000028a418fc Test.strr
R 1 0000000028a418fc Test.str
R 1 0000000028a418fc Test.str[1]
W 1 0000000028a418fc Test.str[0]
W 1 00000000279f2327 SomeClass.staticField
W 1 00000000279f2327 SomeClass.staticField
R 1 00000000279f2327 SomeClass.staticField
W 1 00000000279f2327 SomeClass.staticField
End...
```

### PS

如果上述出现 Permission denied 的情况，请试试 ./jmtrace -jar test/Test.jar Test 。如
果依然存在 Permission denied 的情况，可以输入 chmod 777 jmtrace 给 jmtrace 脚本最高的权限
再试试。

