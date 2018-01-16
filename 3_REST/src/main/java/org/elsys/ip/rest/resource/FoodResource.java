package org.elsys.ip.rest.resource;



import com.opencsv.CSVWriter;
import org.elsys.ip.rest.model.Food;
import org.elsys.ip.rest.service.FoodService;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Path("foods")
public class FoodResource {

    private FoodService foodService = new FoodService();

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Food getFood(@PathParam("id") Integer id) {
        return foodService.getFoodById(id);
    }


    @GET
    @Produces("application/json")
    public Response getFilteredList(@Context UriInfo uriInfo) {

        if(uriInfo.getQueryParameters().isEmpty()) {

            return Response.status(200).entity(  foodService.getFoodList()).build();
        }


        if(uriInfo.getQueryParameters().size() == 1 && uriInfo.getQueryParameters().containsKey("id")) {

            Set<Food> list = foodService.getFoodListByMyltipleId(uriInfo);

            return Response.status(200).entity(list).build();
        }

        return Response.status(200).entity(foodService.getFoodByMultipleParams(uriInfo)).build();
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Food saveFood(Food food) {
        return foodService.saveFood(food);
    }

    @POST
    @Path("/multiple")
    @Consumes("application/json")
    @Produces("application/json")
    public Response saveMultipleFood(List<Food> list){
        List<Food> list1 = foodService.getFoodList();
        list1.addAll(list);
        return Response.ok().entity(list1).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public Food updateFood(@PathParam("id") Integer id, Food food) {
        return foodService.updateFood(id, food);
    }

    @DELETE
    @Path("/{id}")
    public void deleteFood(@PathParam("id") Integer id) {
        foodService.deleteFood(id);
    }



    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response upload(@FormDataParam("file") InputStream inputStream,
                           @FormDataParam("file") FormDataContentDisposition fileDetail) throws IOException {

        OutputStream os = new FileOutputStream("/home/stealth/Desktop/HW2/" + fileDetail.getFileName());
        byte bytes[] = new byte[2048];
        int count = 0;
        while ((count = inputStream.read(bytes)) != -1) {
            os.write(bytes, 0, count);
        }
        os.flush();
        os.close();

        return Response.status(200).build();
    }

    @GET
    @Path("/download")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response download() throws IOException {
        File file = new File("/home/stealth/Desktop/HW2/upload.csv");
        file.createNewFile();
        updateFile();

        return Response
                .ok(file)
                .header("Content-Disposition", "attachment; filename=" + file.getName())
                .build();
    }


    private void updateFile() {
        String file_path = "/home/stealth/Desktop/HW2/";
        try {
            CSVWriter writer = new CSVWriter(new FileWriter(file_path));
            List<Food> foods = foodService.getFoodList();
            List<String[]> csv_data = new ArrayList<>();

            for (Food food : foods) {
                csv_data.add(new String[] { String.valueOf(food.getId()), food.getName(),String.valueOf( food.getCarbs()),
                        String.valueOf(food.getProteins()), String.valueOf(food.getFats()),
                        String.valueOf(food.getUnit()), String.valueOf(food.getCalsPerUnit()),String.valueOf(food.getGlycemicIndex()),
                        String.valueOf(food.getPrice()),String.valueOf(food.getVitaminA())});
            }

            writer.writeAll(csv_data);
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


}
