class NEG extends EXPR_UNARY
{
	NEG(EXPR u)
	{
		this.unaire=u;
	}

	int eval()
	{
		return -unaire.eval();
	}
}