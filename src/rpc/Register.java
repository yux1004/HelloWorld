package rpc;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;
import vo.Person;
import vo.Person.PersonBuilder;
import db.DBConnection;
import db.DBConnectionFactory;
import db.mysql.MySQLConnection;
import rpc.RpcHelper;
//import vo.Data;

/**
 * Servlet implementation class Register
 */
// http://localhost:8080/HelloWorld/index.html
@WebServlet("/register")
public class Register extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /*
        response.setContentType("application/json"); //
        response.addHeader("Access-Control-Allow-Origin", "*");

        String username = "";
        if (request.getParameter("username") != null) {
            username = request.getParameter("username");
        }
        JSONObject obj = new JSONObject();
        try {
            obj.put("username", username);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        PrintWriter out = response.getWriter();
        out.print(obj);
        out.close();
        */
        response.sendRedirect("index.html");
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        String firstName = req.getParameter("firstname");
        String lastName = req.getParameter("lastname");
        String address1 = req.getParameter("address1");
        String address2 = req.getParameter("address2");
        String city = req.getParameter("city");
        String state = req.getParameter("state");
        String zipCodeStr = req.getParameter("zipCode");
        int zipCode = Integer.parseInt(zipCodeStr);
        String country = req.getParameter("country");

        MySQLConnection dbconnect = new MySQLConnection();

        // Check duplicated and empty input at front end.
        boolean isUserExist = dbconnect.isUserExist(firstName, lastName);
        if (firstName != null && lastName != null && address1 != null && address2 != null && city != null
                && state != null && zipCodeStr != null && country != null && isUserExist) {
            req.setAttribute("usernameExist", true);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/duplicated.jsp");
            dispatcher.forward(req, response); //
        } else {
            PersonBuilder builder = new PersonBuilder();
            builder.setFirstName(firstName);
            builder.setLastName(lastName);
            builder.setAddress1(address1);
            builder.setAddress2(address2);
            builder.setCity(city);
            builder.setState(state);
            builder.setZipCode(zipCode);
            builder.setCountry(country);
            Person person = builder.build();

            // use hashmap to test at beginning and then switch to database.
            // Data.users.put(fullName, person);

            dbconnect.addPerson(person);

            req.setAttribute("person", person); // This way it's accessible in JSP by ${persons}
            req.getRequestDispatcher("/WEB-INF/jsp/confirmation.jsp").forward(req, response);

            // 2nd Solution:
            // RequestDispatcher dispatcher =
            // req.getRequestDispatcher("confirmationServlet");
            // dispatcher.include(req, response);

            // 3rd Solution:
            // redirect to JSP to confirm page.
            // RequestDispatcher dispatcher =
            // req.getRequestDispatcher("WEB-INF/jsp/confirmation.jsp");
            // dispatcher.include(req, response); // instead of forward

            // 4th Solution:
            // send JSONObject to javascript to show.
            /*
             * try { JSONObject obj = new JSONObject(); obj.put("firstName", firstName);
             * obj.put("lastName", lastName); obj.put("address1", address1);
             * obj.put("address2", address2); obj.put("city", city); obj.put("state",
             * state); obj.put("zipCode", zipCode); obj.put("country", "US");
             * RpcHelper.writeJsonObject(response, obj); // RequestDispatcher dispatcher =
             * req.getRequestDispatcher("/confirm.html"); } catch (JSONException e) {
             * e.printStackTrace(); }
             */
        }
    }

}
