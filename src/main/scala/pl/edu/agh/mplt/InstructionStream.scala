package pl.edu.agh.mplt

import scala.io.Source
import java.io.File


class InstructionStream(filename: File) {

   private[this] val source: Iterator[String] = Source.fromFile(filename).getLines()
   private[this] var carry                    = ""

   private[this] def getStream: Stream[String] = source.toStream match {
      case stream if stream.isEmpty => Stream.empty[String]
      case stream                   =>
         val line = stream.head
         val (instructions, newCarry) =
            if (line.endsWith(";")) {
               // >= 1 lines without carry
               (((carry ++ line) split ";").map(_ + ";"), "")
            } else {
               // carry exists
               val s = (carry ++ line) split ";"
               (s.dropRight(1).map(_ + ";"), s.last + "\n")
            }
         carry = newCarry


         appendToStream(instructions.toStream)
   }

   def appendToStream(stream: Stream[String]): Stream[String] = stream match {
      case s if s.isEmpty => getStream
      case hd #:: tl      => Stream.cons(hd, appendToStream(tl))
   }

   lazy val instructions: Stream[String] = getStream


}
