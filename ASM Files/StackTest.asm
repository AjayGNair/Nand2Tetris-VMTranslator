// PUSH CONSTANT 17
@17
D=A
@SP
A=M
M=D
@SP
M=M+1

// PUSH CONSTANT 17
@17
D=A
@SP
A=M
M=D
@SP
M=M+1

// EQ 
@SP
AM=M-1
D=M
@SP
AM=M-1
D=M-D
@TRUE0
D;JEQ
@FALSE0
0;JMP
(TRUE0)
@SP
A=M
M=-1
@SP
M=M+1
@END0
0;JMP
(FALSE0)
@SP
A=M
M=0
@SP
M=M+1
(END0)

// PUSH CONSTANT 17
@17
D=A
@SP
A=M
M=D
@SP
M=M+1

// PUSH CONSTANT 16
@16
D=A
@SP
A=M
M=D
@SP
M=M+1

// EQ 
@SP
AM=M-1
D=M
@SP
AM=M-1
D=M-D
@TRUE1
D;JEQ
@FALSE1
0;JMP
(TRUE1)
@SP
A=M
M=-1
@SP
M=M+1
@END1
0;JMP
(FALSE1)
@SP
A=M
M=0
@SP
M=M+1
(END1)

// PUSH CONSTANT 16
@16
D=A
@SP
A=M
M=D
@SP
M=M+1

// PUSH CONSTANT 17
@17
D=A
@SP
A=M
M=D
@SP
M=M+1

// EQ 
@SP
AM=M-1
D=M
@SP
AM=M-1
D=M-D
@TRUE2
D;JEQ
@FALSE2
0;JMP
(TRUE2)
@SP
A=M
M=-1
@SP
M=M+1
@END2
0;JMP
(FALSE2)
@SP
A=M
M=0
@SP
M=M+1
(END2)

// PUSH CONSTANT 892
@892
D=A
@SP
A=M
M=D
@SP
M=M+1

// PUSH CONSTANT 891
@891
D=A
@SP
A=M
M=D
@SP
M=M+1

// LT 
@SP
AM=M-1
D=M
@SP
AM=M-1
D=M-D
@TRUE3
D;JLT
@FALSE3
0;JMP
(TRUE3)
@SP
A=M
M=-1
@SP
M=M+1
@END3
0;JMP
(FALSE3)
@SP
A=M
M=0
@SP
M=M+1
(END3)

// PUSH CONSTANT 891
@891
D=A
@SP
A=M
M=D
@SP
M=M+1

// PUSH CONSTANT 892
@892
D=A
@SP
A=M
M=D
@SP
M=M+1

// LT 
@SP
AM=M-1
D=M
@SP
AM=M-1
D=M-D
@TRUE4
D;JLT
@FALSE4
0;JMP
(TRUE4)
@SP
A=M
M=-1
@SP
M=M+1
@END4
0;JMP
(FALSE4)
@SP
A=M
M=0
@SP
M=M+1
(END4)

// PUSH CONSTANT 891
@891
D=A
@SP
A=M
M=D
@SP
M=M+1

// PUSH CONSTANT 891
@891
D=A
@SP
A=M
M=D
@SP
M=M+1

// LT 
@SP
AM=M-1
D=M
@SP
AM=M-1
D=M-D
@TRUE5
D;JLT
@FALSE5
0;JMP
(TRUE5)
@SP
A=M
M=-1
@SP
M=M+1
@END5
0;JMP
(FALSE5)
@SP
A=M
M=0
@SP
M=M+1
(END5)

// PUSH CONSTANT 32767
@32767
D=A
@SP
A=M
M=D
@SP
M=M+1

// PUSH CONSTANT 32766
@32766
D=A
@SP
A=M
M=D
@SP
M=M+1

// GT 
@SP
AM=M-1
D=M
@SP
AM=M-1
D=M-D
@TRUE6
D;JGT
@FALSE6
0;JMP
(TRUE6)
@SP
A=M
M=-1
@SP
M=M+1
@END6
0;JMP
(FALSE6)
@SP
A=M
M=0
@SP
M=M+1
(END6)

// PUSH CONSTANT 32766
@32766
D=A
@SP
A=M
M=D
@SP
M=M+1

// PUSH CONSTANT 32767
@32767
D=A
@SP
A=M
M=D
@SP
M=M+1

// GT 
@SP
AM=M-1
D=M
@SP
AM=M-1
D=M-D
@TRUE7
D;JGT
@FALSE7
0;JMP
(TRUE7)
@SP
A=M
M=-1
@SP
M=M+1
@END7
0;JMP
(FALSE7)
@SP
A=M
M=0
@SP
M=M+1
(END7)

// PUSH CONSTANT 32766
@32766
D=A
@SP
A=M
M=D
@SP
M=M+1

// PUSH CONSTANT 32766
@32766
D=A
@SP
A=M
M=D
@SP
M=M+1

// GT 
@SP
AM=M-1
D=M
@SP
AM=M-1
D=M-D
@TRUE8
D;JGT
@FALSE8
0;JMP
(TRUE8)
@SP
A=M
M=-1
@SP
M=M+1
@END8
0;JMP
(FALSE8)
@SP
A=M
M=0
@SP
M=M+1
(END8)

// PUSH CONSTANT 57
@57
D=A
@SP
A=M
M=D
@SP
M=M+1

// PUSH CONSTANT 31
@31
D=A
@SP
A=M
M=D
@SP
M=M+1

// PUSH CONSTANT 53
@53
D=A
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

// PUSH CONSTANT 112
@112
D=A
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

// NEG 
@SP
M=M-1
A=M
M=-M
@SP
M=M+1

// AND 
@SP
A=M
A=A-1
A=A-1
D=M
A=A+1
D=D&M
@SP
M=M-1
M=M-1
A=M
M=D
@SP
M=M+1

// PUSH CONSTANT 82
@82
D=A
@SP
A=M
M=D
@SP
M=M+1

// OR 
@SP
A=M
A=A-1
A=A-1
D=M
A=A+1
D=D|M
@SP
M=M-1
M=M-1
A=M
M=D
@SP
M=M+1

// NOT 
@SP
M=M-1
A=M
M=!M
@SP
M=M+1
