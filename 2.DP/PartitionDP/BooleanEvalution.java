public class BooleanEvalution {
    public boolean recursive(int i,int j,String exp){
        if(i == j){
            return exp.charAt(i) == 'T' ? true : false;
        }
        int count = 0;

        for(int ind = i + 1;ind < j;ind+=2){
            boolean left = recursive(i, ind - 1, exp);
            boolean res = false;
            char op = exp.charAt(ind);
            if(op == '&'){
                res = left && recursive(ind + 1, j, exp);
            }else if(op == '|'){
                res = res || recursive(ind + 1, j, exp);
            }else{
                res = res ^ recursive(ind + 1, j, exp);
            }
            if(res){

            }
        }
        return false;
    }
}
