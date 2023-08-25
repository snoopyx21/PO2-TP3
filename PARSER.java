public class PARSER{
    private static String src;
    private static int idx;
    
    private static char last_char;
    private static int last_cst;
    
    private static boolean read_char(char c)
    {
        if((idx < src.length()) && (src.charAt(idx) == c))
        {
            idx++;
            last_char = c;
            return true;
        }
        return false;
    }
    
    private static boolean read_cst()
    {
        last_cst=0;
        boolean retour = false;
        while((idx < src.length()) && 0<=(src.charAt(idx)-'0') && (src.charAt(idx)-'0')<=9)
        {
            last_cst=last_cst*10+(src.charAt(idx)-'0');
            idx++;
            retour = true;
        }
        return retour;
    }
    
    private static EXPR read_e()
    {
        EXPR result, right;
        char op;
        result = read_e_mul();
        if(result != null)
        {
            while(read_char('+') || read_char('-'))
            {
                op = last_char;
                right = read_e_mul();
                if(right==null)
                    error();
                if(op=='+')
                {
                    result = new ADD(result, right);
                }
                else
                {
                    result = new SUB(result, right);
                }
            }
        }
        return result;
    }
    
    private static EXPR read_e_mul()
    {
        EXPR result, right;
        char op;
        result = read_e_unary();
        if(result != null)
        {
            while(read_char('*') || read_char('/'))
            {
                op = last_char;
                right = read_e_unary();
                if(right==null)
                {
                    error();
                }
                if(op=='*')
                {
                    result = new MUL(result, right);
                }
                else
                {
                    result = new DIV(result, right);
                }
            }
        }
        return result;
    }
    
    private static EXPR read_e_unary()
    {
        EXPR result, right;

        if(read_char('-'))
        {
            right = read_e_unary();
            if(right==null)
            {
                error();
            }
            result = new NEG(right);
        }
        else if(read_char('+'))
        {
            right = read_e_unary();
            if(right==null)
            {
                error();
            }
            result = new NEUTRAL(right);
        }
        else
        {
            result = read_e_cst();
        }
        return result;
    }
    
    private static EXPR read_e_cst()
    {
        EXPR result;
        char op;
        
        if(read_char('('))
        {
            result = read_e();
            if(result==null)
            {
                error();
            }
            if(!read_char(')'))
            {
                error();
            }
        }
        else if(read_cst())
        {
            result = new CST(last_cst);
        }
        else
        {
            error();
            result = null;
        }
        return result;
    }
    
    private static void error()
    {
        int j;
        System.out.println(src);
        for(j=0; j<idx; j++)
            System.out.print(' ');
        System.out.println('I');
        System.exit(1);
    }
    
    static EXPR parse_on(String txt)
    {
        EXPR e;
        src = txt;
        idx = 0;
        e = read_e();
        if((e==null) || (idx<src.length()))
            error();
        return e;
    }
}
