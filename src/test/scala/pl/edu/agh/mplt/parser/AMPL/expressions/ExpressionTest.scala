package pl.edu.agh.mplt.parser.AMPL.expressions

import org.scalatest.{Matchers, FlatSpec}
import pl.edu.agh.mplt.parser.logical.expression.{Number, ExpressionAMPLParser}
import pl.edu.agh.mplt.parser.logical.expression.arithmetic.{ArithmeticAMPLParser, Unary}
import pl.edu.agh.mplt.parser.logical.expression.variable.Variable
import pl.edu.agh.mplt.parser.IntercodeImplicits
import pl.edu.agh.mplt.parser.member.StringMember


class ExpressionTest extends FlatSpec with Matchers with IntercodeImplicits {
  val parser = new ExpressionAMPLParser with ArithmeticAMPLParser

  def expr = parser.expr

  def parse(input: String) = parser.parse(expr, input).get

  "Expression Parser" should "parse numbers" in {
    parse("1") should be(Number(1))
    parse("1.1") should be(Number("1.1"))
    parse("0.0") should be(Number("0.0"))
    parse("-3.16") should be(Unary.-(Number("3.16")))
    parse("-3.16e10") should be(Unary.-(Number("3.16e10")))
  }

  it should "parse variable" in {
    parse("A") should be(Variable("A"))
    parse("AlaMaKota") should be(Variable("AlaMaKota"))
    parse("Ala Ma Kota") should not be Variable("Ala Ma Kota")
    parse("12") should not be Variable("12")
  }


}
