; Assembly program to print "exploring"
; For Windows x64 using MASM syntax

.data
    message db "exploring", 0
    message_len equ $ - message - 1

.code
    ; Entry point
    main proc
        ; Get handle to standard output
        mov rcx, -11          ; STD_OUTPUT_HANDLE
        call GetStdHandle
        mov rbx, rax          ; Save handle in rbx
        
        ; Write to console
        mov rcx, rbx          ; Handle
        lea rdx, message      ; Pointer to message
        mov r8, message_len   ; Number of bytes to write
        xor r9, r9            ; Reserved (must be 0)
        push 0                ; lpOverlapped (NULL)
        call WriteFile
        
        ; Exit process
        mov rcx, 0            ; Exit code
        call ExitProcess
    main endp

    ; External function declarations
    extern GetStdHandle: proc
    extern WriteFile: proc
    extern ExitProcess: proc

end
