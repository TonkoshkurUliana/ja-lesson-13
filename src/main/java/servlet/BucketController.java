package servlet;


import domain.Bucket;
import domain.Magazine;
import domain.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import service.BucketService;
import service.MagazineService;
import service.UserService;
import service.impl.BucketServiceImpl;
import service.impl.MagazineServiceImpl;
import service.impl.UserServiceImpl;


import java.io.IOException;
import java.util.Date;
import java.util.UUID;

@WebServlet(name = "bucketController", value = "/bucketController")

public class BucketController extends HttpServlet {

    private BucketService bucketService = BucketServiceImpl.getBucketServiceImpl();

     @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int productId = Integer.parseInt(request.getParameter("magazineId"));

        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");


        Bucket bucket = new Bucket();
        //bucket.setId(UUID.randomUUID().toString());
        bucket.setProductId(productId);
        bucket.setUserId(userId);
        bucket.setPurchaseDate(new Date());

        bucketService.create(bucket);

        response.setContentType("text/plane");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("Success");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bucketId = req.getParameter("bucketId");
        bucketService.delete(Integer.parseInt(bucketId));

        resp.setContentType("text/plane");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write("Success");
    }
}


