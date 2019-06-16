import java.io.{FileOutputStream, PrintWriter}
import scala.collection.mutable

class ImageBuilder {
  private object PieceBuilder {
    def build(name: String): Option[Piece] = {
      name match {
        case "r" => Some(Piece("r", "b"))
        case "n" => Some(Piece("n", "b"))
        case "b" => Some(Piece("b", "b"))
        case "q" => Some(Piece("q", "b"))
        case "k" => Some(Piece("k", "b"))
        case "p" => Some(Piece("p", "b"))

        case "R" => Some(Piece("r", "w"))
        case "N" => Some(Piece("n", "w"))
        case "B" => Some(Piece("b", "w"))
        case "Q" => Some(Piece("q", "w"))
        case "K" => Some(Piece("k", "w"))
        case "P" => Some(Piece("p", "w"))

        case _ => None
      }
    }

  }

  private case class Piece(name: String, color: String) {
    override def toString: String = name+color
    def getImgPath: String = s"img/$name$color.png"
  }

  private val vector: Vector[String] = Vector(
    "a8","b8","c8","d8","e8","f8","g8","h8",
    "a7","b7","c7","d7","e7","f7","g7","h7",
    "a6","b6","c6","d6","e6","f6","g6","h6",
    "a5","b5","c5","d5","e5","f5","g5","h5",
    "a4","b4","c4","d4","e4","f4","g4","h4",
    "a3","b3","c3","d3","e3","f3","g3","h3",
    "a2","b2","c2","d2","e2","f2","g2","h2",
    "a1","b1","c1","d1","e1","f1","g1","h1")

  private val splitBoard: Iterator[Vector[String]] = vector.grouped(8)

  private val coord: mutable.Map[String, (Int, Int)] = mutable.Map[String, (Int, Int)]()

  private var y = 0

  for(j <- splitBoard) {
    for((i, index) <- j.zipWithIndex) coord(i) = (82 + (142 * index), 82 + (142 * y))

    y += 1
  }

  private val header = """<!DOCTYPE svg PUBLIC "-//W3C//DTD SVG 1.1//EN" "http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd">
                 |<svg xmlns="http://www.w3.org/2000/svg" version="1.1" width="1300" height="1300" viewBox="0 0 1300 1300">
                 |	<image href="img/chessboard.jpg" />""".stripMargin

  private val footer ="\n</svg>"

  private def buildCasesFromFEN(FENcases: String): mutable.Map[String, Option[Piece]] = {
    val cases: mutable.Map[String, Option[Piece]] = mutable.Map[String, Option[Piece]]()
    vector.zip(FENcases.split("/").map({
      s => {var newS: String = s
        for (i <- 1.to(8).reverse) newS = newS.replace(i.toString, "-"*i)
        newS.map{case c: Char => PieceBuilder.build(c.toString)}}
    }).toList.flatten).foreach{case (c, piece) => cases(c) = piece.asInstanceOf[Option[Piece]]}
    cases

  }

  private def makeSVG(FENcases: String) = {
    val cases = buildCasesFromFEN(FENcases)

    header + vector.map(c => (c, cases(c))).map{case (a, b) => if (b.isDefined) s"""    <image x="${coord(a)._1}px" y="${coord(a)._2}px" width="142px" height="142px" href="${b.get.getImgPath}"/>"""" else ""}.filterNot(_.isEmpty).mkString("\n") + footer
  }

  def writeSVG(FENcases: String) = {
    val pw = new PrintWriter(new FileOutputStream("img.svg", false))
    pw.write(makeSVG(FENcases))
    pw.close()
  }


}

object ImageBuilder extends App {
  new ImageBuilder().writeSVG(args(0))
}




