package Main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PeriodFlowController {
    
    //Survey
    @Autowired
    SurveyRepository surveyRepository;
    
    @Autowired
    RegistrationRepository registrationRepository;
    
    @Autowired
    SubscriptionRepository subscriptionRepository;

    @Autowired
    TrackerRepository trackerRepository;
	
    public String getLoggedinUser(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof UserDetails){
            
            String username = ((UserDetails) principal).getUsername();
            return username;                
        }
        return principal.toString();
    }

    @GetMapping("/view/{id}")
    public String showUpdateForm(@PathVariable("id") int user_id, Model model) {
    Registration registration = registrationRepository.findById(user_id);
    System.out.println(registration.getName());
    model.addAttribute("registration", registration);
    return "article";
    }
    

    @GetMapping(value = "periodflow/survey")
    public String index(Model model) {
        model.addAttribute("survey", new Survey());
        return "SurveyForm";
    }
    
    @PostMapping(path=  "/add") 
    public String addNewUser (@ModelAttribute Survey ob,Model model) {
        model.addAttribute("ob",ob);
        String email = getLoggedinUser();
        Registration user = registrationRepository.findByEmail(email);
        ob.setRegistration(user);
        surveyRepository.save(ob);
        return "home";
    }
    
    @GetMapping("periodflow/register")
    public String showRegistrationForm(Model model) {
    model.addAttribute("registration", new Registration());
     
    return "SignupPage";
    }

    @PostMapping("/process_register")
    public String processRegister(Registration registration) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(registration.getPassword());
        registration.setPassword(encodedPassword);
        String encodedPassword1 = passwordEncoder.encode(registration.getConfirm_password());
        registration.setConfirm_password(encodedPassword1);
        registrationRepository.save(registration); 
        return "LoginPage";
    }
    
    @GetMapping("/login")
	public String login() {
		return "LoginPage";
    }

    @GetMapping("periodflow/home")
	public String home() {
		return "home";
    }
        
    @GetMapping("periodflow/subscription")
    public String Subsribe(Model model) {
        model.addAttribute("subscription", new Subscription());
        return "Payment";
    }
    
    @RequestMapping("/PaymentDone") 
    public String processSubscription (@ModelAttribute Subscription subs,Model model) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(subs.getPin());
        subs.setPin(encodedPassword);
        model.addAttribute("subs",subs);
        String email = getLoggedinUser();
        Registration user = registrationRepository.findByEmail(email);
        subs.setRegistration(user);
        subscriptionRepository.save(subs);
        return "PaymentDone";
    }

    @GetMapping(value = "periodflow/tracker")
    public String Track(Model model) {
        model.addAttribute("tracker", new Tracker());
        return "TrackerPage";
    }

    //reuqesting mapping does the work of both get and post
    @RequestMapping("/report") 
    public String processTracker(@ModelAttribute Tracker trck,Model model) {
        model.addAttribute("trck",trck);
        String email = getLoggedinUser();
        Registration user = registrationRepository.findByEmail(email);
        trck.setRegistration(user);
        trackerRepository.save(trck);
        return "ReportPage";
    }

    @GetMapping("periodflow/PeriodGuide")
	public String article() {
		return "article";
    }


    private final PaymentService paymentService;

    @Autowired
    public PeriodFlowController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/initiate-payment")
    public String initiatePayment() {
        try {

            String accountId = ""; 
            String accountPassword = ""; 
            String amount = "10"; 
            String fullAmount = "10.350"; 
            String paymentType = "3"; 
            String businessCode = "business_code"; 
            String invoiceKey = "invoice_key"; 
            String returnUrl = ""; 
            String buyerMobile = "buyer_mobile_number"; 
            String buyerEmail = "buyer_email"; 
            String buyerName = "buyer_name"; 
            String paymentMedia = "0"; 

            // Construct and encrypt the request data
            String encryptedData = paymentService.constructAndEncryptRequestData(accountId, accountPassword, amount, fullAmount, paymentType, businessCode, invoiceKey, returnUrl, buyerMobile, buyerEmail, buyerName, paymentMedia);

            // Construct the payment URL
            String paymentUrl = paymentService.buildPaymentUrl(encryptedData, accountId);

            return "redirect:" + paymentUrl;
        } catch (Exception e) {        
            e.printStackTrace(); 
            return "error-page"; 
        }
    }

}
