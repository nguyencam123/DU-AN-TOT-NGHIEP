package com.example.demo.infrastructure.configemail;

import com.example.demo.entities.Homestay;
import com.example.demo.entities.OwnerHomestay;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class RegistrationCompleteEvent extends ApplicationEvent {

    private OwnerHomestay ownerHomestay;
    private String applicationUrl;

    public RegistrationCompleteEvent(OwnerHomestay ownerHomestay, String applicationUrl) {
        super(ownerHomestay);
        this.ownerHomestay = ownerHomestay;
        this.applicationUrl = applicationUrl;
    }

}
