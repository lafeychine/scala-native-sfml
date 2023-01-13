package sfml
package system

import scalanative.unsafe.*
import scala.util.Using

import internal.system.String.*

import stdlib.Locale

class String private[sfml] (private[sfml] val string: Ptr[sfString]) extends Resource:

    def this(ansiString: java.lang.String) =
        this(Resource[sfString] { (r: Ptr[sfString]) =>
            Zone { implicit z =>
                Using.resource(Locale()) { locale =>
                    ctor(r, toCString(ansiString), locale.locale);
                }
            };
        })

    final override def close(): Unit =
        dtor(!(string.asInstanceOf[Ptr[Ptr[Byte]]]))
        Resource.close(string)
