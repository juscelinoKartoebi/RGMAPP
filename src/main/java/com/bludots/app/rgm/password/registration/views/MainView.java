package com.bludots.app.rgm.password.registration.views;

import com.bludots.app.rgm.password.registration.ContextProvider;
import com.bludots.app.rgm.password.registration.services.RequestServices;
import com.bludots.app.rgm.password.registration.valueobjects.RequestVO;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.router.Route;

@Route(value = "")

@CssImport(value = "./styles/components/textfield.css")

public class MainView extends VerticalLayout {

	public MainView() {
		super();
		addClassName("background");
		setSizeFull();
		setJustifyContentMode(JustifyContentMode.CENTER);
		setAlignItems(Alignment.CENTER);

		EmailField email = new EmailField("Insert email");
		email.setLabel("RGM Registration");
		email.setPlaceholder("Insert email");
		email.setWidth("300px");
		email.setHelperText("Welcome on our registration page");
		email.setPattern(null);

		Button submitBtn = new Button("Submit");
		submitBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		submitBtn.addClassName("TestBtn");
		VerticalLayout emailBox = new VerticalLayout(email, submitBtn);
		
		emailBox.addClassName("emailBox");
		add(emailBox);
		submitBtn.addClickListener(new ComponentEventListener<ClickEvent<Button>>() {
			
			@Override
			public void onComponentEvent(ClickEvent<Button> event) {
				RequestServices requestService=  ContextProvider.getBean(RequestServices.class);
				RequestVO requestVO = new RequestVO();
				requestVO.setEmail(email.getValue());
				
				 
				 if(email.getValue().equals("")) {
					 Notification.show("Please enter your email!", 3000, Position.TOP_CENTER);

					 }
				 else {
			    	 requestService.persistRequest(requestVO);
			    	 Notification.show("Please check your inbox for instructions.",4000, Position.TOP_CENTER);
						email.clear();
						
			     }
				
				
			}
		});
	}

}

