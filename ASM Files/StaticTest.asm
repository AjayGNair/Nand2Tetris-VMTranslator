// PUSH CONSTANT 111
@111
D=A
@SP
A=M
M=D
@SP
M=M+1

// PUSH CONSTANT 333
@333
D=A
@SP
A=M
M=D
@SP
M=M+1

// PUSH CONSTANT 888
@888
D=A
@SP
A=M
M=D
@SP
M=M+1

// POP STATIC 8
@SP
M=M-1
A=M
D=M
@StaticTest.vm.static.8
M=D

// POP STATIC 3
@SP
M=M-1
A=M
D=M
@StaticTest.vm.static.3
M=D

// POP STATIC 1
@SP
M=M-1
A=M
D=M
@StaticTest.vm.static.1
M=D

// PUSH STATIC 3
@StaticTest.vm.static.3
D=M
@SP
A=M
M=D
@SP
M=M+1

// PUSH STATIC 1
@StaticTest.vm.static.1
D=M
@SP
A=M
M=D
@SP
M=M+1

// SUB 
@SP
A=M
A=A-1
A=A-1
D=M
A=A+1
D=D-M
@SP
M=M-1
M=M-1
A=M
M=D
@SP
M=M+1

// PUSH STATIC 8
@StaticTest.vm.static.8
D=M
@SP
A=M
M=D
@SP
M=M+1

// ADD 
@SP
A=M
A=A-1
A=A-1
D=M
A=A+1
D=D+M
@SP
M=M-1
M=M-1
A=M
M=D
@SP
M=M+1

