import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import okhttp3.*;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.List;

public class Main {
    public static void main(String[] args){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        GitHubService service = retrofit.create(GitHubService.class);
        Call<List<Repo>> repos = service.listRepos("MaFe1401");
        try{
            List<Repo> res =repos.execute().body();
            for (Repo r : res){
                System.out.println(r);
            }
        }
        catch(Exception e){
            System.out.println("EXCEPCIÓN:");
            System.out.println(e.toString());
        }
    }
}

