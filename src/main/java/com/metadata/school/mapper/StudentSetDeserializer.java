//package com.metadata.school.mapper;
//
//import java.io.IOException;
//import java.util.HashSet;
//import java.util.Set;
//
//import com.fasterxml.jackson.core.JsonParser;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.DeserializationContext;
//import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
//import com.metadata.school.entity.Student;
//
//public class StudentSetDeserializer extends StdDeserializer<Set<Student>>{
//
//
//	private static final long serialVersionUID = 5655580299885635864L;
//	
//	public StudentSetDeserializer() {
//        this(null);
//    }
//
//	protected StudentSetDeserializer(Class<?> vc) {
//		super(vc);
//	}
//
//	@Override
//	public Set<Student> deserialize(JsonParser p, DeserializationContext ctxt)
//			throws IOException, JsonProcessingException {
//		// TODO Auto-generated method stub
//		 return new HashSet<>();
//	}
//
//}
