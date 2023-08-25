class NEUTRAL extends EXPR_UNARY
{
	NEUTRAL(EXPR u)
	{
		this.unaire=u;
	}

	int eval()
	{
		return unaire.eval();
	}
}