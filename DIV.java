class DIV extends EXPR_BINARY
{
	DIV(EXPR l, EXPR r)
	{
		this.left=l;
		this.right=r;
	}

	int eval()
	{
		if (right.eval() == 0)
		{
			System.out.println("division par zero : impossible \n");
			System.exit(1);
		}
		return left.eval()/right.eval();
	}
}