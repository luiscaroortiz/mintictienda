package controladores;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import controladores.Usuarios;

public class Json {

	private static URL url;
	private static String sitio = "http://localhost:5000/";

	////////////////////////////////////////////////////////////
	//////////////////////////////////////usuarios

	//agregar informacion a la tabla usuario
	public static ArrayList<Usuarios> parsingUsuarios(String json) throws ParseException {//devulve un arraylist
		JSONParser jsonParser = new JSONParser();
		ArrayList<Usuarios> lista = new ArrayList<Usuarios>();
		JSONArray usuarios = (JSONArray) jsonParser.parse(json);
		Iterator i = usuarios.iterator(); //recorrer la tabla usuario
		while (i.hasNext()) {
			JSONObject innerObj = (JSONObject) i.next();
			Usuarios usuario = new Usuarios();
			usuario.setCedula_usuario(Long.parseLong(innerObj.get("cedula_usuario").toString())); //convertir de String a Long
			usuario.setEmail_usuario(innerObj.get("email_usuario").toString());
			usuario.setNombre_usuario(innerObj.get("nombre_usuario").toString());
			usuario.setPassword(innerObj.get("password").toString());
			usuario.setUsuario(innerObj.get("usuario").toString());
			lista.add(usuario);
		}
		return lista;
	}
	
	
	//listar la informacion
	public static ArrayList<Usuarios> getJSON() throws IOException, ParseException { //devolver un listado JSON

		url = new URL(sitio + "usuarios/listar"); //trae el metodo de Usuarios.API 
		HttpURLConnection http = (HttpURLConnection) url.openConnection();

		http.setRequestMethod("GET");
		http.setRequestProperty("Accept", "application/json");

		InputStream respuesta = http.getInputStream();
		byte[] inp = respuesta.readAllBytes();
		String json = "";

		for (int i = 0; i < inp.length; i++) {
			json += (char) inp[i];
		}

		ArrayList<Usuarios> lista = new ArrayList<Usuarios>();
		lista = parsingUsuarios(json);
		http.disconnect();
		return lista;
	}

	public static int postJSON(Usuarios usuario) throws IOException {

		url = new URL(sitio + "usuarios/guardar");
		HttpURLConnection http;
		http = (HttpURLConnection) url.openConnection();

		try {
			http.setRequestMethod("POST");
		} catch (ProtocolException e) {
			e.printStackTrace();
		}

		http.setDoOutput(true);
		http.setRequestProperty("Accept", "application/json");
		http.setRequestProperty("Content-Type", "application/json");

		String data = "{" + "\"cedula_usuario\":\"" + String.valueOf(usuario.getCedula_usuario())
				+ "\",\"email_usuario\": \"" + usuario.getEmail_usuario() + "\",\"nombre_usuario\": \""
				+ usuario.getNombre_usuario() + "\",\"password\":\"" + usuario.getPassword() + "\",\"usuario\":\""
				+ usuario.getUsuario() + "\"}";
		byte[] out = data.getBytes(StandardCharsets.UTF_8);
		OutputStream stream = http.getOutputStream();
		stream.write(out);

		int respuesta = http.getResponseCode();
		http.disconnect();
		return respuesta;
	}

	public static int putJSON(Usuarios usuario, Long id) throws IOException {

		url = new URL(sitio+"usuarios/actualizar");
		HttpURLConnection http;
		http = (HttpURLConnection)url.openConnection();

		try {
			http.setRequestMethod("PUT");
		} catch (ProtocolException e) {
			e.printStackTrace();
		}

		http.setDoOutput(true);
		http.setRequestProperty("Accept", "application/json");
		http.setRequestProperty("Content-Type", "application/json");

		String data = "{"
				+ "\"cedula_usuario\":\""+ id
				+"\",\"email_usuario\": \""+usuario.getEmail_usuario()
				+"\",\"nombre_usuario\": \""+usuario.getNombre_usuario()
				+"\",\"password\":\""+usuario.getPassword()
				+"\",\"usuario\":\""+usuario.getUsuario()
				+ "\"}";
		byte[] out = data.getBytes(StandardCharsets.UTF_8);
		OutputStream stream = http.getOutputStream();
		stream.write(out);

		int respuesta = http.getResponseCode();
		http.disconnect();
		return respuesta;
	}

	public static int deleteJSON(long id) throws IOException {

		url = new URL(sitio+"usuarios/eliminar/" + id);
		HttpURLConnection http;
		http = (HttpURLConnection)url.openConnection();

		try {
			http.setRequestMethod("DELETE");
		} catch (ProtocolException e) {
			e.printStackTrace();
		}

		http.setDoOutput(true);
		http.setRequestProperty("Accept", "application/json");
		http.setRequestProperty("Content-Type", "application/json");


		int respuesta = http.getResponseCode();
		http.disconnect();
		return respuesta;
	}

	////////////////////////////////////////////////////////////
	//////////////////////////////////////clientes

	//agregar informacion a la tabla clientes
	public static ArrayList<Clientes> parsingClientes(String json) throws ParseException {//devulve un arraylist
		JSONParser jsonParser = new JSONParser();
		ArrayList<Clientes> lista = new ArrayList<Clientes>();
		JSONArray clientes = (JSONArray) jsonParser.parse(json);
		Iterator i = clientes.iterator(); //recorrer la tabla cliente
		while (i.hasNext()) {
			JSONObject innerObj = (JSONObject) i.next();
			Clientes cliente = new Clientes();
			cliente.setCedula_cliente(Long.parseLong(innerObj.get("cedula_cliente").toString())); //convertir de String a Long
			cliente.setEmail_cliente(innerObj.get("email_cliente").toString());
			cliente.setNombre_cliente(innerObj.get("nombre_cliente").toString());
			cliente.setTelefono_cliente(innerObj.get("telefono_cliente").toString());
			cliente.setDireccion_cliente(innerObj.get("direccion_cliente").toString());
			lista.add(cliente);
		}
		return lista;
	}

	//listar la informacion
	public static ArrayList<Clientes> getJSON_clientes() throws IOException, ParseException { //devolver un listado JSON

		url = new URL(sitio + "clientes/listar"); //trae el metodo de Clientes.API 
		HttpURLConnection http = (HttpURLConnection) url.openConnection();

		http.setRequestMethod("GET");
		http.setRequestProperty("Accept", "application/json");

		InputStream respuesta = http.getInputStream();
		byte[] inp = respuesta.readAllBytes();
		String json = "";

		for (int i = 0; i < inp.length; i++) {
			json += (char) inp[i];
		}

		ArrayList<Clientes> lista = new ArrayList<Clientes>();
		lista = parsingClientes(json);
		http.disconnect();
		return lista;
	}

	public static int postJSON_clientes(Clientes cliente) throws IOException {

		url = new URL(sitio + "clientes/guardar");
		HttpURLConnection http;
		http = (HttpURLConnection) url.openConnection();

		try {
			http.setRequestMethod("POST");
		} catch (ProtocolException e) {
			e.printStackTrace();
		}

		http.setDoOutput(true);
		http.setRequestProperty("Accept", "application/json");
		http.setRequestProperty("Content-Type", "application/json");

		String data = "{" + "\"cedula_cliente\":\"" + String.valueOf(cliente.getCedula_cliente())
				+ "\",\"email_cliente\": \"" + cliente.getEmail_cliente() + "\",\"nombre_cliente\": \""
				+ cliente.getNombre_cliente() + "\",\"telefono_cliente\":\"" + cliente.getTelefono_cliente() + "\",\"direccion_cliente\":\""
				+ cliente.getDireccion_cliente() + "\"}";
		byte[] out = data.getBytes(StandardCharsets.UTF_8);
		OutputStream stream = http.getOutputStream();
		stream.write(out);

		int respuesta = http.getResponseCode();
		http.disconnect();
		return respuesta;
	}

	public static int putJSON_clientes(Clientes cliente, Long id) throws IOException {

		url = new URL(sitio+"clientes/actualizar");
		HttpURLConnection http;
		http = (HttpURLConnection)url.openConnection();

		try {
			http.setRequestMethod("PUT");
		} catch (ProtocolException e) {
			e.printStackTrace();
		}

		http.setDoOutput(true);
		http.setRequestProperty("Accept", "application/json");
		http.setRequestProperty("Content-Type", "application/json");

		String data = "{"
				+ "\"cedula_cliente\":\""+ id
				+"\",\"email_cliente\": \""+cliente.getEmail_cliente()
				+"\",\"nombre_cliente\": \""+cliente.getNombre_cliente()
				+"\",\"telefono_cliente\":\""+cliente.getTelefono_cliente()
				+"\",\"direccion_cliente\":\""+cliente.getDireccion_cliente()
				+ "\"}";
		byte[] out = data.getBytes(StandardCharsets.UTF_8);
		OutputStream stream = http.getOutputStream();
		stream.write(out);

		int respuesta = http.getResponseCode();
		http.disconnect();
		return respuesta;
	}

	public static int deleteJSON_clientes(long id) throws IOException {

		url = new URL(sitio+"clientes/eliminar/" + id);
		HttpURLConnection http;
		http = (HttpURLConnection)url.openConnection();

		try {
			http.setRequestMethod("DELETE");
		} catch (ProtocolException e) {
			e.printStackTrace();
		}

		http.setDoOutput(true);
		http.setRequestProperty("Accept", "application/json");
		http.setRequestProperty("Content-Type", "application/json");


		int respuesta = http.getResponseCode();
		http.disconnect();
		return respuesta;
	}

	
	////////////////////////////////////////////////////////////
	//////////////////////////////////////proveedores

	//agregar informacion a la tabla proveedores
	public static ArrayList<Proveedores> parsingProveedores(String json) throws ParseException {//devulve un arraylist
		JSONParser jsonParser = new JSONParser();
		ArrayList<Proveedores> lista = new ArrayList<Proveedores>();
		JSONArray proveedores = (JSONArray) jsonParser.parse(json);
		Iterator i = proveedores.iterator(); //recorrer la tabla Proveedore
		while (i.hasNext()) {
			JSONObject innerObj = (JSONObject) i.next();
			Proveedores proveedor = new Proveedores();
			proveedor.setNitproveedor(Long.parseLong(innerObj.get("nitproveedor").toString())); //convertir de String a Long
			proveedor.setCiudad_proveedor(innerObj.get("ciudad_proveedor").toString());
			proveedor.setNombre_proveedor(innerObj.get("nombre_proveedor").toString());
			proveedor.setTelefono_proveedor(innerObj.get("telefono_proveedor").toString());
			proveedor.setDireccion_proveedor(innerObj.get("direccion_proveedor").toString());
			lista.add(proveedor);
		}
		return lista;
	}

	//listar la informacion
	public static ArrayList<Proveedores> getJSON_proveedores() throws IOException, ParseException { //devolver un listado JSON

		url = new URL(sitio + "proveedores/listar"); //trae el metodo de proveedores.API 
		HttpURLConnection http = (HttpURLConnection) url.openConnection();

		http.setRequestMethod("GET");
		http.setRequestProperty("Accept", "application/json");

		InputStream respuesta = http.getInputStream();
		byte[] inp = respuesta.readAllBytes();
		String json = "";

		for (int i = 0; i < inp.length; i++) {
			json += (char) inp[i];
		}

		ArrayList<Proveedores> lista = new ArrayList<Proveedores>();
		lista = parsingProveedores(json);
		http.disconnect();
		return lista;
	}

	public static int postJSON_proveedores(Proveedores proveedor) throws IOException {

		url = new URL(sitio + "proveedores/guardar");
		HttpURLConnection http;
		http = (HttpURLConnection) url.openConnection();

		try {
			http.setRequestMethod("POST");
		} catch (ProtocolException e) {
			e.printStackTrace();
		}

		http.setDoOutput(true);
		http.setRequestProperty("Accept", "application/json");
		http.setRequestProperty("Content-Type", "application/json");

		String data = "{" + "\"nitproveedor\":\"" + String.valueOf(proveedor.getNitproveedor())
				+ "\",\"ciudad_proveedor\": \"" + proveedor.getCiudad_proveedor() + "\",\"nombre_proveedor\": \""
				+ proveedor.getNombre_proveedor() + "\",\"telefono_proveedor\":\"" + proveedor.getTelefono_proveedor() + "\",\"direccion_proveedor\":\""
				+ proveedor.getDireccion_proveedor() + "\"}";
		byte[] out = data.getBytes(StandardCharsets.UTF_8);
		OutputStream stream = http.getOutputStream();
		stream.write(out);

		int respuesta = http.getResponseCode();
		http.disconnect();
		return respuesta;
	}

	public static int putJSON_proveedores(Proveedores proveedor, Long id) throws IOException {

		url = new URL(sitio+"proveedores/actualizar");
		HttpURLConnection http;
		http = (HttpURLConnection)url.openConnection();

		try {
			http.setRequestMethod("PUT");
		} catch (ProtocolException e) {
			e.printStackTrace();
		}

		http.setDoOutput(true);
		http.setRequestProperty("Accept", "application/json");
		http.setRequestProperty("Content-Type", "application/json");

		String data = "{"
				+ "\"nitproveedor\":\""+ id
				+"\",\"ciudad_proveedor\": \""+proveedor.getCiudad_proveedor()
				+"\",\"nombre_proveedor\": \""+proveedor.getNombre_proveedor()
				+"\",\"telefono_proveedor\":\""+proveedor.getTelefono_proveedor()
				+"\",\"direccion_proveedor\":\""+proveedor.getDireccion_proveedor()
				+ "\"}";
		byte[] out = data.getBytes(StandardCharsets.UTF_8);
		OutputStream stream = http.getOutputStream();
		stream.write(out);

		int respuesta = http.getResponseCode();
		http.disconnect();
		return respuesta;
	}

	public static int deleteJSON_proveedores(long id) throws IOException {

		url = new URL(sitio+"proveedores/eliminar/" + id);
		HttpURLConnection http;
		http = (HttpURLConnection)url.openConnection();

		try {
			http.setRequestMethod("DELETE");
		} catch (ProtocolException e) {
			e.printStackTrace();
		}

		http.setDoOutput(true);
		http.setRequestProperty("Accept", "application/json");
		http.setRequestProperty("Content-Type", "application/json");

		int respuesta = http.getResponseCode();
		http.disconnect();
		return respuesta;
	}

	
	////////////////////////////////////////////////////////////
	//////////////////////////////////////productos

	//agregar informacion a la tabla productos
	public static ArrayList<Productos> parsingProductos(String json) throws ParseException {//devulve un arraylist
		JSONParser jsonParser = new JSONParser();
		ArrayList<Productos> lista = new ArrayList<Productos>();
		JSONArray productos = (JSONArray) jsonParser.parse(json);
		Iterator i = productos.iterator(); //recorrer la tabla Productos
		while (i.hasNext()) {
			JSONObject innerObj = (JSONObject) i.next();
			Productos producto = new Productos();
			producto.setCodigo_producto(Long.parseLong(innerObj.get("codigo_producto").toString())); //convertir de String a Long
			producto.setIvacompra(Double.parseDouble(innerObj.get("ivacompra").toString()));
			producto.setNitproveedor(Long.parseLong(innerObj.get("nitproveedor").toString())); //convertir de String a Long
			producto.setNombre_producto(innerObj.get("nombre_producto").toString());
			producto.setPrecio_compra(Double.parseDouble(innerObj.get("precio_compra").toString()));
			producto.setPrecio_venta(Double.parseDouble(innerObj.get("precio_venta").toString()));
			lista.add(producto);
		}
		return lista;
	}

	//listar la informacion
	public static ArrayList<Productos> getJSON_productos() throws IOException, ParseException { //devolver un listado JSON

		url = new URL(sitio + "productos/listar"); //trae el metodo de productos.API 
		HttpURLConnection http = (HttpURLConnection) url.openConnection();

		http.setRequestMethod("GET");
		http.setRequestProperty("Accept", "application/json");

		InputStream respuesta = http.getInputStream();
		byte[] inp = respuesta.readAllBytes();
		String json = "";

		for (int i = 0; i < inp.length; i++) {
			json += (char) inp[i];
		}

		ArrayList<Productos> lista = new ArrayList<Productos>();
		lista = parsingProductos(json);
		http.disconnect();
		return lista;
	}

	public static int postJSON_productos(Productos producto) throws IOException {

		url = new URL(sitio + "productos/guardar");
		HttpURLConnection http;
		http = (HttpURLConnection) url.openConnection();

		try {
			http.setRequestMethod("POST");
		} catch (ProtocolException e) {
			e.printStackTrace();
		}

		http.setDoOutput(true);
		http.setRequestProperty("Accept", "application/json");
		http.setRequestProperty("Content-Type", "application/json");

		String data = "{" + "\"codigo_producto\":\"" + String.valueOf(producto.getCodigo_producto())
				+ "\",\"ivacompra\": \"" + producto.getIvacompra() + "\",\"nitproveedor\": \""
				+ String.valueOf(producto.getNitproveedor()) + "\",\"nombre_producto\":\"" + producto.getNombre_producto() + "\",\"precio_compra\":\""
				+ producto.getPrecio_compra() + "\",\"precio_venta\":\"" + producto.getPrecio_venta() + "\"}";
		byte[] out = data.getBytes(StandardCharsets.UTF_8);
		OutputStream stream = http.getOutputStream();
		stream.write(out);

		int respuesta = http.getResponseCode();
		http.disconnect();
		return respuesta;
	}

	public static int putJSON_productos(Productos producto, Long id) throws IOException {

		url = new URL(sitio+"productos/actualizar");
		HttpURLConnection http;
		http = (HttpURLConnection)url.openConnection();

		try {
			http.setRequestMethod("PUT");
		} catch (ProtocolException e) {
			e.printStackTrace();
		}

		http.setDoOutput(true);
		http.setRequestProperty("Accept", "application/json");
		http.setRequestProperty("Content-Type", "application/json");

		String data = "{"
				+ "\"codigo_producto\":\""+ id
				+"\",\"ivacompra\": \""+producto.getIvacompra()
				+"\",\"nitproveedor\": \""+producto.getNitproveedor()
				+"\",\"nombre_producto\": \""+producto.getNombre_producto()
				+"\",\"precio_compra\":\""+producto.getPrecio_compra()
				+"\",\"precio_venta\":\""+producto.getPrecio_venta()
				+ "\"}";
		byte[] out = data.getBytes(StandardCharsets.UTF_8);
		OutputStream stream = http.getOutputStream();
		stream.write(out);

		int respuesta = http.getResponseCode();
		http.disconnect();
		return respuesta;
	}

	public static int deleteJSON_productos(long id) throws IOException {

		url = new URL(sitio+"productos/eliminar/" + id);
		HttpURLConnection http;
		http = (HttpURLConnection)url.openConnection();

		try {
			http.setRequestMethod("DELETE");
		} catch (ProtocolException e) {
			e.printStackTrace();
		}

		http.setDoOutput(true);
		http.setRequestProperty("Accept", "application/json");
		http.setRequestProperty("Content-Type", "application/json");

		int respuesta = http.getResponseCode();
		http.disconnect();
		return respuesta;
	}

////////////////////////////////////////////////////////////
//////////////////////////////////////Ventas

//agregar informacion a la tabla clientes
	public static ArrayList<Ventas> parsingVentas(String json) throws ParseException {// devulve un arraylist
		JSONParser jsonParser = new JSONParser();
		ArrayList<Ventas> lista = new ArrayList<Ventas>();
		JSONArray ventas = (JSONArray) jsonParser.parse(json);
		Iterator i = ventas.iterator(); // recorrer la tabla ventas
		while (i.hasNext()) {
			JSONObject innerObj = (JSONObject) i.next();
			Ventas venta = new Ventas();
			venta.setCodigo_venta(Long.parseLong(innerObj.get("Codigo_venta").toString())); // convertir de String a
																							// Long
			venta.setCedula_cliente(Long.parseLong(innerObj.get("Cedula_cliente").toString()));
			venta.setCedula_usuario(Long.parseLong(innerObj.get("Cedula_usuario").toString()));
			venta.setIvaventa(Double.parseDouble(innerObj.get("Ivaventa").toString()));
			venta.setTotal_venta(Double.parseDouble(innerObj.get("Total_venta").toString()));
			venta.setValor_venta(Double.parseDouble(innerObj.get("setValor_venta").toString()));
			lista.add(venta);
		}
		return lista;
	}

//listar la informacion
	public static ArrayList<Ventas> getJSON_ventas() throws IOException, ParseException { // devolver un listado JSON

		url = new URL(sitio + "ventas/listar"); // trae el metodo de ventas.API
		HttpURLConnection http = (HttpURLConnection) url.openConnection();

		http.setRequestMethod("GET");
		http.setRequestProperty("Accept", "application/json");

		InputStream respuesta = http.getInputStream();
		byte[] inp = respuesta.readAllBytes();
		String json = "";

		for (int i = 0; i < inp.length; i++) {
			json += (char) inp[i];
		}

		ArrayList<Ventas> lista = new ArrayList<Ventas>();
		lista = parsingVentas(json);
		http.disconnect();
		return lista;
	}

	public static int postJSON_ventas(Ventas ventas) throws IOException {

		url = new URL(sitio + "ventas/guardar");
		HttpURLConnection http;
		http = (HttpURLConnection) url.openConnection();

		try {
			http.setRequestMethod("POST");
		} catch (ProtocolException e) {
			e.printStackTrace();
		}

		http.setDoOutput(true);
		http.setRequestProperty("Accept", "application/json");
		http.setRequestProperty("Content-Type", "application/json");

		String data = "{" + "\"codigo_venta\":\"" + String.valueOf(ventas.getCodigo_venta())
				+ "\",\"cedula_cliente\": \"" + ventas.getCedula_cliente() + "\",\"cedula_usuario\": \""
				+ ventas.getCedula_usuario() + "\",\"ivaventa\":\"" + ventas.getIvaventa() + "\",\"total_venta\":\""
				+ ventas.getTotal_venta() + "\",\"valor_venta\": \"" + ventas.getValor_venta() + "\"}";
		byte[] out = data.getBytes(StandardCharsets.UTF_8);
		OutputStream stream = http.getOutputStream();
		stream.write(out);

		int respuesta = http.getResponseCode();
		http.disconnect();
		return respuesta;
	}

	public static int putJSON_ventas(Ventas ventas, Long id) throws IOException {

		url = new URL(sitio + "ventas/actualizar");
		HttpURLConnection http;
		http = (HttpURLConnection) url.openConnection();

		try {
			http.setRequestMethod("PUT");
		} catch (ProtocolException e) {
			e.printStackTrace();
		}

		http.setDoOutput(true);
		http.setRequestProperty("Accept", "application/json");
		http.setRequestProperty("Content-Type", "application/json");

		String data = "{" + "\"codigo_venta\":\"" + id + "\",\"cedula_cliente\": \"" + ventas.getCedula_cliente()
				+ "\",\"cedula_usuario\": \"" + ventas.getCedula_usuario() + "\",\"ivaventa\":\"" + ventas.getIvaventa()
				+ "\",\"total_venta\":\"" + ventas.getTotal_venta() + "\",\"valor_venta\":\"" + ventas.getValor_venta()
				+ "\"}";
		byte[] out = data.getBytes(StandardCharsets.UTF_8);
		OutputStream stream = http.getOutputStream();
		stream.write(out);

		int respuesta = http.getResponseCode();
		http.disconnect();
		return respuesta;
	}

	public static int deleteJSON_ventas(long id) throws IOException {

		url = new URL(sitio + "ventas/eliminar/" + id);
		HttpURLConnection http;
		http = (HttpURLConnection) url.openConnection();

		try {
			http.setRequestMethod("DELETE");
		} catch (ProtocolException e) {
			e.printStackTrace();
		}

		http.setDoOutput(true);
		http.setRequestProperty("Accept", "application/json");
		http.setRequestProperty("Content-Type", "application/json");

		int respuesta = http.getResponseCode();
		http.disconnect();
		return respuesta;
	}
////////////////////////////////////////////////////////////
//////////////////////////////////////Detalle_Ventas

	public static int postJSON_Detalle_Venta(Detalle_ventas detalle_ventas) throws IOException {

		url = new URL(sitio + "detalle_ventas/guardar");
		HttpURLConnection http;
		http = (HttpURLConnection) url.openConnection();

		try {
			http.setRequestMethod("POST");
		} catch (ProtocolException e) {
			e.printStackTrace();
		}

		http.setDoOutput(true);
		http.setRequestProperty("Accept", "application/json");
		http.setRequestProperty("Content-Type", "application/json");

		String data = "{" + "\"codigo_detalle_venta\":\"" + String.valueOf(detalle_ventas.getCodigo_detalle_venta())
				+ "\",\"cantidad_producto\": \"" + String.valueOf(detalle_ventas.getCantidad_producto())
				+ "\",\"codigo_producto\": \"" + String.valueOf(detalle_ventas.getCodigo_producto())
				+ "\",\"codigo_venta\": \"" + String.valueOf(detalle_ventas.getCodigo_venta())
				+ "\",\"valor_total\": \"" + String.valueOf(detalle_ventas.getValor_total()) + "\",\"valor_venta\":\""
				+ String.valueOf(detalle_ventas.getValor_venta()) + "\",\"valor_iva\":\""
				+ String.valueOf(detalle_ventas.getValor_iva()) + "\"}";
		byte[] out = data.getBytes(StandardCharsets.UTF_8);
		OutputStream stream = http.getOutputStream();
		stream.write(out);

		int respuesta = http.getResponseCode();
		http.disconnect();
		return respuesta;
}


}
