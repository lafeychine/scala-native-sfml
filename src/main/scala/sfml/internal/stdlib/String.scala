package sfml
package internal
package stdlib

import scalanative.unsafe.*

@extern object String:
    type stdString = CStruct3[CString, CSize, CSize]

    @name("_ZNSt7__cxx1112basic_stringIcSt11char_traitsIcESaIcEE9_M_createERmm")
    def ctor(self: Ptr[stdString], str: CString): Unit = extern
