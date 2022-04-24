package sfml
package internal
package graphics

import scalanative.unsafe.*

@link("csfml-graphics")
@extern object Font:
    type sfFont

    def sfFont_createFromFile(filename: CString): Ptr[sfFont] = extern
    def sfFont_destroy(font: Ptr[sfFont]): Unit = extern
