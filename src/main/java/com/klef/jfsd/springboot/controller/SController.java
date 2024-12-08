package com.klef.jfsd.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.klef.jfsd.springboot.model.Courses;
import com.klef.jfsd.springboot.model.Register;
import com.klef.jfsd.springboot.model.TimeTable; // Import the Timetable model
import com.klef.jfsd.springboot.repo.CoursesRepo;
import com.klef.jfsd.springboot.springboot.service.StudentService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class SController {

    @Autowired
    private StudentService service;

    private HttpSession session;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    
    @GetMapping("/login")
    public String LoginPage() {
        return "login";
    }

    
    @GetMapping("/signup")
    public String RegisterPage() {
        return "signup";
    }

    @GetMapping("/getstarted")
    public String Getstarted() {
        return "getstarted";
    }
    @GetMapping("/course_schedule")
    public String course_started() {
        return "course_schedule";
    }
    @GetMapping("/students")
    public String Students() {
        return "students";
    }
    @GetMapping("/schedule")
    public String showSchedule() {
        return "schedule"; // This will look for schedule.html in templates directory
    }
    @GetMapping("/feedback")
    public String feedback() {
        return "feedback"; // This will look for schedule.html in templates directory
    }
    @GetMapping("/sfeedback")
    public String sfeedback() {
        return "sfeedback"; // This will look for schedule.html in templates directory
    }
    @GetMapping("/mycourses")
    public String mycourses() {
        return "mycourses"; // This will look for schedule.html in templates directory
    }
    @GetMapping("/timeout")
    public String timeout() {
        return "timeout"; // This will look for schedule.html in templates directory
    }

    @Controller
    public class ProfilePageController {
        @GetMapping("/profilepage")
        public String getProfilePage(Model model, HttpServletRequest request) {
            String name = (String) request.getSession().getAttribute("name");
            String email = (String) request.getSession().getAttribute("email");
            String phone = (String) request.getSession().getAttribute("phone");
            String dob = (String) request.getSession().getAttribute("dob");
            String course = (String) request.getSession().getAttribute("course");
            String yearOfStudy = (String) request.getSession().getAttribute("yearOfStudy");
            String gender = (String) request.getSession().getAttribute("gender");
            String address = (String) request.getSession().getAttribute("address");
            String type = (String) request.getSession().getAttribute("type");

            model.addAttribute("name", name);
            model.addAttribute("email", email);
            model.addAttribute("phone", phone);
            model.addAttribute("dob", dob);
            model.addAttribute("course", course);
            model.addAttribute("yearOfStudy", yearOfStudy);
            model.addAttribute("gender", gender);
            model.addAttribute("address", address);
            model.addAttribute("type", type);

            return "profilepage";
        }
    }

    @GetMapping("/registration")
    public String getRegistrationPage(Model model) {
        List<Courses> courses = StudentService.getAllCoursesData(); // Fetch from DB
        model.addAttribute("courses", courses);
        return "registration"; // register.html template
    }

    @GetMapping("/facultydashboard")
    public String FacultyDashboard(Model model, HttpServletRequest request) {
        String name = (String) request.getSession().getAttribute("name");
        model.addAttribute("name", name);
        return "facultyDashboard";  // The name of your HTML file
    }

    @GetMapping("/studentdashboard")
    public String StudentDashboard(Model model, HttpServletRequest request) {
        String name = (String) request.getSession().getAttribute("name");
        model.addAttribute("name", name);
        return "studentdashboard"; 
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        // Invalidate the session
        HttpSession session = request.getSession();
        session.invalidate();

        // Add a flash attribute to show the success message
        redirectAttributes.addFlashAttribute("message", "User logged out successfully!");

        // Redirect to the login page
        return "redirect:/login"; // Assuming '/login' is your login page URL
    }

    @PostMapping("/userdetails")
    public String registerDetails(@ModelAttribute Register register, 
                                  @RequestParam("confirmpassword") String confirmPassword,
                                  Model model) {
        if (!register.getPassword().equals(confirmPassword)) {
            model.addAttribute("error", "Passwords do not match");
            return "register";  // Returns the registration page with the error message
        }

        try {
            Register registeredUser = service.registrationPageDetails(register);
            System.out.println("User registered successfully with ID: " + registeredUser.getName());
            return "login";  // Redirect to login page after successful registration
        } catch (RuntimeException ex) {
            model.addAttribute("error", ex.getMessage());
            return "register";  // Returns to registration page with error message
        }
    }

    @PostMapping("/login")
    public String LoginDetails(@RequestParam String id,
                               @RequestParam String password,
                               Model model,
                               HttpServletRequest request) {
        Register register = service.loginPage(id, password);
        if (register == null) {
            model.addAttribute("message", "Invalid details");
            return "login";
        } else {
            session = request.getSession();
            session.setAttribute("name", register.getName());
            session.setAttribute("password", register.getPassword());
            session.setAttribute("email", register.getEmailid());
            session.setAttribute("type", register.getType());
            model.addAttribute("name", register.getName());
            if ("faculty".equalsIgnoreCase(register.getType())) {
                return "redirect:/facultydashboard";  
            } else if ("student".equalsIgnoreCase(register.getType())) {
                return "redirect:/studentdashboard";  
            } else {
                return "Dashboard";
            }
        }
    }


}
