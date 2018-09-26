public class Player {
    private String token;
    private int id;
    public String username;

    public Player(){

    }

    public Player(int id, String username){
        this.id = id;
        this.username = username;
    }

    public boolean login(String username, String pswd){
        // 这里读txt获取uername和pswd
        if(username.equals("test") && pswd.equals("test")){
            this.token = "tiancheng's token";
            return true;
        }
        return false;
    }

    public boolean engage(){
        return false;
    }

}
