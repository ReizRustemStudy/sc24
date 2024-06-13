package com.reizovrustem.study.manager.controller.payload;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record NewProductPayload(
		
		@NotNull
		@Size(min = 3, max = 5)
		String title, 
		@Size(max = 1000)
		String details) {

}
