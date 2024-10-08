package registro;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.sostmaky.tp1lab3.login.MainActivity;
import com.sostmaky.tp1lab3.model.Usuario;
import com.sostmaky.tp1lab3.request.ApiCliente;

public class RegistroActivityViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Usuario>MtUsus;

    public RegistroActivityViewModel(@NonNull Application application) {
        super(application);
        context=application.getApplicationContext();
    }

    public LiveData<Usuario> getMUsuario() {
        if (MtUsus == null) {
            MtUsus = new MutableLiveData<>();
        }
        return MtUsus;
    }



    public void leerusuario(Intent intent){
        Bundle bundle= intent.getBundleExtra("datos");
        if (bundle!=null){
            Usuario usuario=(Usuario)bundle.getSerializable("usuario");
            if (usuario!=null){
                MtUsus.setValue(usuario);
            }
        }
    }

public void guardarUsuario(String dni,String apellido,String nombre, String mail, String contrasena ) {
        long dn=Long.parseLong(dni);
        Usuario usuario=new Usuario(dn,apellido,nombre,mail,contrasena);
        ApiCliente.guardarObjeto(context, usuario);
        Toast.makeText(context, "Usuario guardado", Toast.LENGTH_SHORT).show();
        Intent intent =new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

}
