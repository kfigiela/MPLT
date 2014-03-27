package pl.edu.agh.mplt.parser.AMPL.statements.sexpr

import org.scalatest.{Matchers, FlatSpec}
import pl.edu.agh.mplt.parser.formula.set.{SetExpressionAMPLParser, ExplicitSet, SetComprehension}
import pl.edu.agh.mplt.parser.formula.expression.ExpressionAMPLParser
import pl.edu.agh.mplt.parser.formula.expression.arithmetic.ArithmeticAMPLParser
import pl.edu.agh.mplt.parser.formula.expression.Number
import pl.edu.agh.mplt.parser.member.{MemberAMPLParser, StringMember, Member}
import pl.edu.agh.mplt.parser.IntercodeImplicits
import pl.edu.agh.mplt.parser.reference.{ReferenceParser, SetReference}

class SetExpressionTest extends FlatSpec with Matchers with IntercodeImplicits {
  val parser = new SetExpressionAMPLParser with ExpressionAMPLParser with ArithmeticAMPLParser with MemberAMPLParser with ReferenceParser

  def expr = parser.sexpr

  def parse(input: String) = parser.parse(expr, input).get

  "Set expr parser" should "parse explicit number set definition" in {
    parse("{1, 2, 3}") should be(ExplicitSet(Set[Member](Number(1), Number(2), Number(3))))
  }

  it should "parse reference to set" in {
    parse("a") should be(SetReference("a"))
  }

  it should "parse one element set literal" in {
    parse("{ \"a\" }") should be(ExplicitSet(Set[Member](StringMember("a"))))
  }

  it should "parse explicit string literal set definition" in {
    parse( """{"a", "b", "c"}""") should be(ExplicitSet(Set[Member](StringMember("a"), StringMember("b"), StringMember("c"))))
  }

  it should "parse empty set literal" in {
    parse("{}") should be(ExplicitSet(Set.empty))
  }

  it should "parse number set comprehension" in {
    parse("1 .. 10") should be(SetComprehension(1, 10))
  }

  it should "parse string set comprehension" in {
    parse( """ "a" .. "f" """) should be(SetComprehension(StringMember("a"), StringMember("f")))
  }

  it should "parse number set comprehension with step" in {
    parse("1 .. 17 by 5") should be(SetComprehension(1, 17, 5))
  }

  it should "parse string set comprehension with step" in {
    parse( """ "a" .. "d" by 5""") should be(SetComprehension(StringMember("a"), StringMember("d"), 5))
  }

  it should "parse binary union" in {
    parse(" {1, 2, 3} union 1 ..7 by 2") should be
  }

  it should "parse binary inter" in {
    parse(" {1, 2, 3} inter 1 ..7 by 2") should be
  }

  it should "parse diff" in {
    parse(" {1, 2, 3} diff 1 ..7 by 2") should be
  }

  it should "parse symdiff" in {
    parse(" {1, 2, 3} symdiff 1 ..7 by 2") should be
  }

  it should "parse cross" in {
    parse(" {1, 2, 3} union 1 ..7 by 2") should be
  }

  it should "parse indexing union" in {
    parse(" union {i in A}  1 ..7 by 2") should be
  }

  it should "parse indexing  inter" in {
    parse(" inter {i in A}  1 ..7 by 2") should be
  }

}
