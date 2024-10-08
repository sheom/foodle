package com.sheom.foodle.controller;


import com.sheom.foodle.Exception.UserException;
import com.sheom.foodle.config.JwtProvider;
import com.sheom.foodle.model.Cart;
import com.sheom.foodle.model.PasswordResetToken;
import com.sheom.foodle.model.User;
import com.sheom.foodle.repository.CartRepository;
import com.sheom.foodle.repository.UserRepository;
import com.sheom.foodle.request.LoginRequest;
import com.sheom.foodle.request.ResetPasswordRequest;
import com.sheom.foodle.response.ApiResponse;
import com.sheom.foodle.response.AuthResponse;
import com.sheom.foodle.service.CustomeUserServiceImplementation;
import com.sheom.foodle.service.PasswordResetTokenService;
import com.sheom.foodle.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;
	private JwtProvider jwtProvider;
	private CustomeUserServiceImplementation customUserDetails;
	private CartRepository cartRepository;

    private PasswordResetTokenService passwordResetTokenService;

    private UserService userService;

	public AuthController(UserRepository userRepository, 
			PasswordEncoder passwordEncoder, 
			JwtProvider jwtProvider,
			CustomeUserServiceImplementation customUserDetails,
			CartRepository cartRepository,
			PasswordResetTokenService passwordResetTokenService,
			UserService userService
			) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtProvider = jwtProvider;
		this.customUserDetails = customUserDetails;
		this.cartRepository=cartRepository;
		this.passwordResetTokenService=passwordResetTokenService;
		this.userService=userService;

	}

	@PostMapping("/signup")
	public ResponseEntity<AuthResponse> createUserHandler(@Valid @RequestBody User user) throws UserException {

		String email = user.getEmail();
		String password = user.getPassword();
		String fullName = user.getFullName();
		String role=user.getRole();

		User isEmailExist = userRepository.findByEmail(email);

		if (isEmailExist!=null) {

			throw new UserException("Email Is Already Used With Another Account");
		}

		// Create new user
		User createdUser = new User();
		createdUser.setEmail(email);
		createdUser.setFullName(fullName);
		createdUser.setPassword(passwordEncoder.encode(password));
		createdUser.setRole(role);

		User savedUser = userRepository.save(createdUser);
		
		Cart cart = new Cart();
		cart.setCustomer(savedUser);
		Cart savedCart = cartRepository.save(cart);
//		savedUser.setCart(savedCart);
		userRepository.save(savedUser);
		
	
		
		

		Authentication authentication = new UsernamePasswordAuthenticationToken(email, password);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		String token = jwtProvider.generateToken(authentication);

		AuthResponse authResponse = new AuthResponse();
		authResponse.setJwt(token);
		authResponse.setMessage("Register Success");

		return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.OK);

	}

	@PostMapping("/signin")
	public ResponseEntity<AuthResponse> signin(@RequestBody LoginRequest loginRequest) {

		String username = loginRequest.getEmail();
		String password = loginRequest.getPassword();

		System.out.println(username + " ----- " + password);

		Authentication authentication = authenticate(username, password);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		String token = jwtProvider.generateToken(authentication);
		AuthResponse authResponse = new AuthResponse();

		authResponse.setMessage("Login Success");
		authResponse.setJwt(token);

		return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.OK);
	}

	private Authentication authenticate(String username, String password) {
		UserDetails userDetails = customUserDetails.loadUserByUsername(username);

		System.out.println("sign in userDetails - " + userDetails);

		if (userDetails == null) {
			System.out.println("sign in userDetails - null " + userDetails);
			throw new BadCredentialsException("Invalid username or password");
		}
		if (!passwordEncoder.matches(password, userDetails.getPassword())) {
			System.out.println("sign in userDetails - password not match " + userDetails);
			throw new BadCredentialsException("Invalid username or password");
		}
		return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	}

	@PostMapping("/reset-password")
	 public ResponseEntity<ApiResponse> resetPassword(
	    		
	    		@RequestBody ResetPasswordRequest req) throws UserException {
	        
	        PasswordResetToken resetToken = passwordResetTokenService.findByToken(req.getToken());

	        if (resetToken == null ) {
	        	throw new UserException("token is required...");
	        }
	        if(resetToken.isExpired()) {
	        	passwordResetTokenService.delete(resetToken);
	        	throw new UserException("token get expired...");
	        
	        }

	        // Update user's password
	        User user = resetToken.getUser();
	        userService.updatePassword(user, req.getPassword());

	        // Delete the token
	        passwordResetTokenService.delete(resetToken);
	        
	        ApiResponse res=new ApiResponse();
	        res.setMessage("Password updated successfully.");
	        res.setStatus(true);

	        return ResponseEntity.ok(res);
	    }
	 
	 @PostMapping("/reset-password-request")
	    public ResponseEntity<ApiResponse> resetPassword(@RequestParam("email") String email) throws UserException {
	        User user = userService.findUserByEmail(email);
	        System.out.println("ResetPasswordController.resetPassword()");

	        if (user == null) {
	        	throw new UserException("user not found");
	        }

	        userService.sendPasswordResetEmail(user);

	        ApiResponse res=new ApiResponse();
	        res.setMessage("Password reset email sent successfully.");
	        res.setStatus(true);

	        return ResponseEntity.ok(res);
	    }
	    
}
