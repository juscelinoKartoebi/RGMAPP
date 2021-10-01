package com.bludots.app.rgm.password.registration.views;

import java.time.LocalDateTime;

import java.util.List;
import java.util.Map;

import com.bludots.app.rgm.password.registration.ContextProvider;
import com.bludots.app.rgm.password.registration.repositories.entities.Request;
import com.bludots.app.rgm.password.registration.services.CredentialService;
import com.bludots.app.rgm.password.registration.services.RequestServices;
import com.bludots.app.rgm.password.registration.valueobjects.CredentialVO;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Location;
import com.vaadin.flow.router.OptionalParameter;
import com.vaadin.flow.router.QueryParameters;
import com.vaadin.flow.router.Route;

@Route(value = "pwd")
@CssImport(value = "./styles/components/textfield2.css")

public class PasswordFormView extends VerticalLayout implements HasUrlParameter<String> {

	Request request;
	private String token;

	public PasswordFormView() {
		super();
		addClassName("background");
	}

	@Override
	public void setParameter(BeforeEvent event, @OptionalParameter String parameter) {

		Location location = event.getLocation();
		QueryParameters queryParameters = location.getQueryParameters();

		Map<String, List<String>> parametersMap = queryParameters.getParameters();

		List<String> tokenList = parametersMap.get("token");
		if (tokenList != null && !tokenList.isEmpty()) {
			token = tokenList.get(0);

			RequestServices requestService = ContextProvider.getBean(RequestServices.class);
			request = requestService.findRequest(token);

			if (request != null) {
				LocalDateTime expireTime = request.getRequestDateTime().plusMinutes(1);
				if (expireTime.isAfter(LocalDateTime.now())) {
					buildPasswordFormView();
				} else {
					event.forwardTo(MainView.class);
//					UI.getCurrent().navigate("token/expired");
				}
			} else {
				event.forwardTo(MainView.class);
			}
		} else {
			event.forwardTo(MainView.class);
		}
	}

	private void buildPasswordFormView() {
//		addClassName("background"); 

		setSizeFull();
		setJustifyContentMode(JustifyContentMode.CENTER);
		setAlignItems(Alignment.CENTER);

		PasswordField password1 = new PasswordField("Password");
		password1.setPlaceholder("New password");
		password1.setWidth("300px");
		add(password1);

		PasswordField password2 = new PasswordField("Confirm password");
		password2.setPlaceholder("Confirm password");
		password2.setWidth("300px");
		password2.setHelperText("Welcome on our registration page");

		EmailField emailfield = new EmailField("Insert email");
		emailfield.setValue(request.getEmail());
		emailfield.setReadOnly(true);

		emailfield.setLabel("RGM Registration");
		emailfield.setPlaceholder("Insert email");
		emailfield.setWidth("300px");
		emailfield.setPattern(null);

		Button submitbtn = new Button("Register");
		submitbtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		submitbtn.addClassName("subBtn");
		VerticalLayout emailBox = new VerticalLayout(emailfield, password1, password2, submitbtn);
//		add(emailfield, password1, password2, submitbtn);
		emailBox.addClassName("emailForm");
		add(emailBox);
		submitbtn.addClickListener(new ComponentEventListener<ClickEvent<Button>>() {

			@Override
			public void onComponentEvent(ClickEvent<Button> event) {
				CredentialVO vo = new CredentialVO();
				vo.setEmail(emailfield.getValue());
				vo.setPassword(password1.getValue());
				vo.setConfirmPassword(password2.getValue());

				CredentialService changePasswordService = ContextProvider.getBean(CredentialService.class);
				changePasswordService.persistPasswordChange(vo);

				if (password1.getValue().isEmpty() && password2.getValue().isEmpty()) {
					Notification.show("Insert a password.", 4000, Position.TOP_CENTER);

				} else if (password1.getValue().equals(password2.getValue())
						&& emailfield.getValue().equals(request.getEmail()) && !password1.isEmpty()
						&& !password2.isEmpty()) {
					Notification.show("Registration is succesful.", 4000, Position.TOP_CENTER);
					emailfield.clear();
					password1.clear();
					password2.clear();
				}else if(!password1.getValue().equals(password2.getValue())){
					Notification.show("Passwords do not match.", 4000, Position.TOP_CENTER);
				
				} else {
					Notification.show("Your credentials are incorrect.", 4000, Position.TOP_CENTER);
				}

			}
		});
	}
}
