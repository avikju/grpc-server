package com.grpc.poc.service;

import com.grpc.poc.User.APIResponse;
import com.grpc.poc.User.Empty;
import com.grpc.poc.User.LoginRequest;
import com.grpc.poc.userGrpc.userImplBase;

import io.grpc.stub.StreamObserver;

public class UserService extends userImplBase {

	@Override
	public void login(LoginRequest request, StreamObserver<APIResponse> responseObserver) {
		System.out.println("Inside login");
		//request is coming from the client call
		String userName=request.getUsername();
		String password=request.getPassword();
		
		//this is how response object is built
		APIResponse.Builder response=APIResponse.newBuilder();
		
		//stupid logic just for demo
		if(userName.equals(password)) {
			response.setResponseCode(0);
			response.setResponsemessage("Success");
		}else {
			response.setResponseCode(100);
			response.setResponsemessage("Invalid username/password");
		}
		
		//we need to send back the response object
		responseObserver.onNext(response.build());
		//complete the request
		responseObserver.onCompleted();
	}

	@Override
	public void logout(Empty request, StreamObserver<APIResponse> responseObserver) {
		System.out.println("Inside logout");
	}
	

}
