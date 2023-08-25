class MUL extends EXPR_BINARY
{
	MUL(EXPR l, EXPR r)
	{
		this.left=l;
		this.right=r;
	}

	int eval()
	{
		return left.eval() * right.eval();
	}
}