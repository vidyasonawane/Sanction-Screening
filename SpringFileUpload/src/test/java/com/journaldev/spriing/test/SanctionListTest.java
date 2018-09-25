package com.journaldev.spriing.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.journaldev.spring.database.database_class;

public class SanctionListTest {

	@Test
	public void test() {
		database_class o2=new database_class();
		 List<String> actual = o2.conn();
	        List<String> expected = Arrays.asList("tejashree shete", "sharayu shinde", "vidya sonawane");
	       
			
	        assertThat(actual, is(expected));

	       

	    }
	}


