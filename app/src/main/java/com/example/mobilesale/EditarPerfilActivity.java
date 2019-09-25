package com.example.mobilesale;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditarPerfilActivity extends AppCompatActivity {

    private static final String TAG = EditarPerfilActivity.class.getSimpleName() ;
    private TextView name, email, pass, apellidos, telefono, direccion ;
    private Spinner genero;
    private Button btn_photo_upload;
    SessionManager sessionManager;
    String getID;
    private static String URL_READ = "http://mobilesale.tech/read_detail.php";
    private static String URL_EDIT = "http://mobilesale.tech/edit_detail.php";
    private static String URL_UPLOAD = "http://mobilesale.tech/upload.php";
    private static String URL_DELETE = "http://mobilesale.tech/delete.php";

    private Menu action;
    private Bitmap bitmap;
    CircleImageView profile_image;

    private final int PHOTO_CODE = 100;
    private final int SELECT_PICTURE = 200;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_perfil);

        sessionManager = new SessionManager(this);
        sessionManager.checkLoggin();

        name = findViewById(R.id.editNombre);
        email = findViewById(R.id.editEmail);
        pass = findViewById(R.id.editPass);
        apellidos = findViewById(R.id.editApellidos);
        telefono = findViewById(R.id.editTelefono);
        direccion = findViewById(R.id.editDireccion);
        genero = findViewById(R.id.spinnerGenero);
        btn_photo_upload = findViewById(R.id.btn_photo);
        profile_image = findViewById(R.id.profile_image);

        HashMap<String, String> user = sessionManager.getUserDetail();
        getID = user.get(sessionManager.ID);

        btn_photo_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                choosefile();

            }
        });


        //inicio spinner
        final String[] genero = new String[] {"Hombre", "Mujer" };

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,    android.R.layout.simple_spinner_item, genero);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner combo = (Spinner) findViewById(R.id.spinnerGenero);
        combo.setAdapter(adaptador);


        //fin spinner
    }
    //READ
    private void getUserDetail(){

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("loading...");
        progressDialog.show();

        final StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_READ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        Log.i(TAG,response.toString());

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("read");

                            if (success.equals("1")){

                                for (int i = 0 ; i < jsonArray.length(); i++){

                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String strName = object.getString("name").trim();
                                    String strEmail = object.getString("email").trim();
                                    String strPass = object.getString("pass").trim();
                                    String strApellidos = object.getString("apellidos").trim();
                                    String strTelefono = object.getString("telefono").trim();
                                    String strDireccion = object.getString("direccion").trim();
                                    String strPhoto = object.getString("photo");

                                    //obtener imagen
                                    Toast.makeText(EditarPerfilActivity.this,"url:"+strPhoto,Toast.LENGTH_LONG).show();

                                    //Picasso.with(EditarPerfilActivity.this)
                                     //       .load(strPhoto)
                                       //     .into(profile_image);



                                    cargarWebserviceImagen(strPhoto);

                                    //String strGenero = object.getString("genero").trim();

                                    name.setText(strName);
                                    email.setText(strEmail);
                                    pass.setText(strPass);
                                    apellidos.setText(strApellidos);
                                    telefono.setText(strTelefono);
                                    direccion.setText(strDireccion);
                                }

                            }

                        }catch (JSONException e){
                            e.printStackTrace();
                            progressDialog.dismiss();
                            Toast.makeText(EditarPerfilActivity.this,"Error leyendo"+e.toString(),Toast.LENGTH_SHORT).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(EditarPerfilActivity.this,"Error leyendo"+error.toString(),Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id",getID);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void cargarWebserviceImagen(String photo) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        ImageRequest imageRequest = new ImageRequest(photo, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                profile_image.setImageBitmap(response);

            }
        }, 0, 0, ImageView.ScaleType.CENTER_CROP, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(EditarPerfilActivity.this, "Error al cargar imagen", Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(imageRequest);

    }

    @Override
    protected void onResume() {
        super.onResume();
        getUserDetail();
    }


    //SAVE

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_perfil,menu);

        action = menu;
        action.findItem(R.id.menu_save).setVisible(false);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.menu_edit:
                name.setFocusableInTouchMode(true);
                email.setFocusableInTouchMode(true);
                pass.setFocusableInTouchMode(true);
                apellidos.setFocusableInTouchMode(true);
                telefono.setFocusableInTouchMode(true);
                direccion.setFocusableInTouchMode(true);
                genero.setFocusableInTouchMode(true);


                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(name, InputMethodManager.SHOW_IMPLICIT);

                action.findItem(R.id.menu_edit).setVisible(false);
                action.findItem(R.id.menu_save).setVisible(true);

                return true;

            case R.id.menu_save:

                SaveEditDetail();

                action.findItem(R.id.menu_edit).setVisible(true);
                action.findItem(R.id.menu_save).setVisible(false);

                name.setFocusableInTouchMode(false);
                email.setFocusableInTouchMode(false);
                pass.setFocusableInTouchMode(false);
                apellidos.setFocusableInTouchMode(false);
                telefono.setFocusableInTouchMode(false);
                direccion.setFocusableInTouchMode(false);
                genero.setFocusableInTouchMode(false);


                name.setFocusable(false);
                email.setFocusable(false);
                pass.setFocusable(false);
                apellidos.setFocusable(false);
                telefono.setFocusable(false);
                direccion.setFocusable(false);
                genero.setFocusable(false);


                return true;

            case R.id.eliminar_cuenta:
                Log.i("====>", "Click en eliminar cuenta!!");
                AlertDialog.Builder b = new AlertDialog.Builder(this);
                b.setTitle("Alerta Cuenta");
                b.setMessage("Estas seguro de eliminar tu cuenta?");
                b.setCancelable(false);
                b.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        eliminarUsuario(URL_DELETE);
                        Intent intent = new Intent(EditarPerfilActivity.this,LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
                b.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        dialogInterface.cancel();

                    }
                });
                AlertDialog dialog = b.create();
                dialog.show();

                return true;

            case R.id.cancelar:
                Log.i("====>", "Click en Cancelar!!");
                Intent cancelar = new Intent(this,HomeActivity.class);
                startActivity(cancelar);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

     private void SaveEditDetail() {


         final String name = this.name.getText().toString().trim();
         final String email = this.email.getText().toString().trim();
         final String pass = this.pass.getText().toString().trim();
         final String apellidos = this.apellidos.getText().toString().trim();
         final String telefono = this.telefono.getText().toString().trim();
         final String direccion = this.direccion.getText().toString().trim();
         final String genero = this.genero.getSelectedItem().toString();
         final String id = getID;

         final ProgressDialog progressDialog = new ProgressDialog(this);
         progressDialog.setMessage("Saving...");
         progressDialog.show();

         StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_EDIT,
                 new Response.Listener<String>() {
                     @Override
                     public void onResponse(String response) {
                         progressDialog.dismiss();

                         try {
                             JSONObject jsonObject = new JSONObject(response);
                             String success = jsonObject.getString("success");

                             if (success.equals("1")) {
                                 Toast.makeText(EditarPerfilActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                                 sessionManager.createSession(name, email, id);
                             }
                         } catch (JSONException e) {
                             e.printStackTrace();
                             progressDialog.dismiss();
                             Toast.makeText(EditarPerfilActivity.this, "Error" + e.toString(), Toast.LENGTH_SHORT).show();
                         }

                     }
                 },
                 new Response.ErrorListener() {
                     @Override
                     public void onErrorResponse(VolleyError error) {
                         progressDialog.dismiss();
                         Toast.makeText(EditarPerfilActivity.this, "Error" + error.toString(), Toast.LENGTH_SHORT).show();

                     }
                 }) {
             @Override
             protected Map<String, String> getParams() throws AuthFailureError {
                 Map<String, String> params = new HashMap<>();
                 params.put("name", name);
                 params.put("email", email);
                 params.put("pass", pass);
                 params.put("apellidos", apellidos);
                 params.put("telefono", telefono);
                 params.put("direccion", direccion);
                 params.put("genero", genero);
                 params.put("id", id);
                 return params;
             }
         };

         RequestQueue requestQueue = Volley.newRequestQueue(this);
         requestQueue.add(stringRequest);

     }

     //UPLOADIMAGE
     private void choosefile(){
         final CharSequence[] options = {"Tomar foto","Elegir foto","Cancelar"};
         final AlertDialog.Builder builder = new AlertDialog.Builder(EditarPerfilActivity.this);
         builder.setTitle("Elige una opcion");
         builder.setItems(options, new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialogInterface, int seleccion) {
                 if (options[seleccion]=="Tomar foto"){
                     openCamara();
                 }else if(options[seleccion]=="Elegir foto"){
                     Intent intent = new Intent();
                     intent.setType("image/*");
                     intent.setAction(Intent.ACTION_GET_CONTENT);
                     startActivityForResult(intent.createChooser(intent,"Select Picture"),SELECT_PICTURE);
                 }else if(options[seleccion]=="Cancelar"){
                     dialogInterface.dismiss();
                 }
             }
         });
         builder.show();
     }

     private void openCamara(){
         Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
         startActivityForResult(intent,PHOTO_CODE);

     }

     @Override
     protected void onActivityResult(int requestCode, int resultCode, Intent data) {
         super.onActivityResult(requestCode, resultCode, data);

         switch (requestCode) {
             case SELECT_PICTURE:
                 if (resultCode == RESULT_OK && data != null && data.getData() != null) {
                     Uri filePath = data.getData();
                     try {
                         bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                         profile_image.setImageBitmap(bitmap);
                     } catch (IOException e) {
                         e.printStackTrace();
                     }

                     UploadPicture(getID, getStringImage(bitmap));
                 }
                 break;
             case PHOTO_CODE:
                 if (resultCode == RESULT_OK) {

                         bitmap = (Bitmap) data.getExtras().get("data");
                         profile_image.setImageBitmap(bitmap);


                     UploadPicture(getID, getStringImage(bitmap));
                 }
                 break;
         }

     }


    private void UploadPicture(final String id, final String photo){

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Cargando...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,URL_UPLOAD,
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        Log.i(TAG,response.toString());
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            if (success.equals("1")){
                                Toast.makeText(EditarPerfilActivity.this,"Success!",Toast.LENGTH_SHORT).show();


                            }
                        }catch (JSONException e){
                            e.printStackTrace();
                            progressDialog.dismiss();
                            Toast.makeText(EditarPerfilActivity.this,"Intenta nuvamente"+e.toString(),Toast.LENGTH_SHORT).show();
                        }


                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(EditarPerfilActivity.this,"Intenta nuvamente"+error.toString(),Toast.LENGTH_SHORT).show();

                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("id", id);
                params.put("photo",photo);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
    private String getStringImage(Bitmap bitmap) {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100, byteArrayOutputStream);

        byte[] imageByteArray = byteArrayOutputStream.toByteArray();
        String encodeImage = Base64.encodeToString(imageByteArray,Base64.DEFAULT);

        return encodeImage;
    }

    //Eliminar cuenta
    private void eliminarUsuario(String URL_DELETE){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DELETE, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(EditarPerfilActivity.this,"Usuario Eliminado",Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(EditarPerfilActivity.this,"Error al eliminar" + error.toString(),Toast.LENGTH_SHORT).show();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id",getID);
                return params;

            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}
