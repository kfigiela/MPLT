package pl.edu.agh.mplt.parser.declaration.constraint

import pl.edu.agh.mplt.parser.formula.expression.Expression
import pl.edu.agh.mplt.parser.ASTNode

case class ConstraintExpression(leftExpression: Option[ConstraintComparison] = None,
                                expr: Expression,
                                rightExpression: Option[ConstraintComparison] = None) extends ASTNode

trait ConstraintComparison extends ASTNode

object Constraint {

  case class <=(expr: Expression) extends ConstraintComparison

  case class ===(expr: Expression) extends ConstraintComparison

  case class >=(expr: Expression) extends ConstraintComparison

}