package sfml
package system

import scalanative.unsafe.*
import scala.util.Using

import internal.system.String.*

import stdlib.Locale

type ScalaString = java.lang.String

class String private[sfml] (self: Ptr[sfStringFields])
        extends Resource[Ptr[sfString]]:

    private[sfml] def bind = self

    def this(ansiString: ScalaString) =
        this(Resource[sfStringFields, sfString] { (r: Ptr[sfString]) =>
            Zone { implicit z =>
                Using.resource(Locale()) { locale =>
                    ctor(r, toCString(ansiString), locale.bind);
                }
            };
        })

    def close(): Unit =
      Resource.close(dtor)(bind)
