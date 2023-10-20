package JogoVelha;

public class Area {
    private char simbolo;
    public Area(){
        this.simbolo = ' ';
    }
    public char getSimbolo(){
        return this.simbolo;
    }
    public void setSimbolo(char escolha){
        if(this.simbolo == ' '){
            this.simbolo = escolha;
        } else{
            System.out.println("Campo já utilizado, escolha outro!");
        }
    }
}
