class Complex_Num{
    String num;
    double des_part;
    double mni_part;

    Complex_Num(){
        this(0, 0);
    }

    Complex_Num(int des_part, int mni_part){
        this.des_part = des_part;
        this.mni_part = mni_part;
    }

    void make_int(){
        if(num == null){
            des_part = 0;
            mni_part = 0;
            return;
        }

        String s = num.trim().replace(" ", "");
        int sign_pos = -1;
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(c == '+' || c == '-') {
                sign_pos = i;
                break;
            }
        }

        try{
            if(sign_pos == -1){
                if(s.endsWith("i")){
                    String mni = s.substring(0, s.length() - 1);
                    des_part = 0;
                    if(mni.isEmpty() || mni.equals("+")){
                        mni_part = 1;
                    }
                    else if(mni.equals("-")){
                        mni_part = -1;
                    }
                    else{
                        mni_part = Double.parseDouble(mni);
                    }
                }
                else{
                    des_part = Double.parseDouble(s);
                    mni_part =0;
                }
            }
            else{
                des_part = Double.parseDouble(s.substring(0, sign_pos));
                String mni_str = s.substring(sign_pos, s.length() - 1);
                if(mni_str.equals("+")){
                    mni_part = 1;
                }
                else if(mni_str.equals("-")){
                    mni_part = -1;
                }
                else{
                    mni_part = Double.parseDouble(mni_str);
                }
            }
        } catch(NumberFormatException | StringIndexOutOfBoundsException e){
            System.out.println("не удалось разобрать число " + num + " поэтому ваше число принимает вид 0 + 0i");
            des_part = 0;
            mni_part = 0;
        }
    }

    double[] get_res(){
        make_int();
        return new double[] {des_part, mni_part};
    }
}