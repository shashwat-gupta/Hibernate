package com.service.controller;

import java.util.List;

import javax.websocket.server.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.service.dao.MyProductImpl;
import com.service.model.Product;

@Path("prodserv")
public class ProductController {


	MyProductImpl mimpl = new MyProductImpl();
@Path("getprod")
@GET
@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
public List<Product> getlist() throws Exception
{
	return mimpl.products();
}

@Path("addprod")
@POST
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.TEXT_PLAIN)
public String addprod(Product p) throws Exception
{
	mimpl.addProduct(p);
	return "Product Added";
}


@Path("nameprice/{name}/{price}")
@GET
@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
public List<Product> getlist2(@PathParam("name") String name,@PathParam("price") int price) throws Exception
{
	return mimpl.byNameOrPrice(name, price);
}

@Path("update")
@PUT
@Produces(MediaType.TEXT_PLAIN)
@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
public String updateprod(Product p) throws Exception
{
	mimpl.updateProduct(p);
	return "Product Added";
}

@Path("Delete/{id}")
@DELETE
@Produces(MediaType.TEXT_PLAIN)
public String dodelete(@PathParam("id") int id) throws Exception
{
	mimpl.deleteProduct(id);
	return "Product Deleted";
}
}
