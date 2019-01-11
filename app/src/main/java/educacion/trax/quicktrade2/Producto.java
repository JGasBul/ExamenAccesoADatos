package educacion.trax.quicktrade2;

import android.os.Parcel;
import android.os.Parcelable;

public class Producto implements Parcelable {
    String nombre;
    String desc;
    String categoria;
    double precio;
    String user;

    public Producto() {
    }

    public Producto(String nombre, String desc, String categoria, double precio, String user) {
        this.nombre = nombre;
        this.desc = desc;
        this.categoria = categoria;
        this.precio = precio;
        this.user = user;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    protected Producto(Parcel in) {
        nombre = in.readString();
        desc = in.readString();
        categoria = in.readString();
        precio = in.readDouble();
        user = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeString(desc);
        dest.writeString(categoria);
        dest.writeDouble(precio);
        dest.writeString(user);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Producto> CREATOR = new Parcelable.Creator<Producto>() {
        @Override
        public Producto createFromParcel(Parcel in) {
            return new Producto(in);
        }

        @Override
        public Producto[] newArray(int size) {
            return new Producto[size];
        }
    };
}