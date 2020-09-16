package br.com.alex.converter;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.alex.converter.mocks.MockPerson;
import br.com.alex.data.model.Person;
import br.com.alex.data.vo.v1.PersonVO;

public class DozerConverterTest {
	
    MockPerson inputObject;

    @Before
    public void setUp() {
        inputObject = new MockPerson();
    }

    @Test
    public void parseEntityToVOTest() {
        PersonVO output = DozerConverter.parseObject(inputObject.mockEntity(), PersonVO.class);
        Assert.assertEquals(Long.valueOf(1L), output.getKey());
        Assert.assertEquals("First Name Test1", output.getFirstName());
        Assert.assertEquals("Last Name Test1", output.getLastName());
        Assert.assertEquals("Address Test1", output.getAddress());
        Assert.assertEquals("Female", output.getGender());
    }

    @Test
    public void parseEntityListToVOListTest() {
        List<PersonVO> outputList = DozerConverter.parseListObjects(inputObject.mockEntityList(), PersonVO.class);
        PersonVO outputZero = outputList.get(0);
        
        Assert.assertEquals(Long.valueOf(1L), outputZero.getKey());
        Assert.assertEquals("First Name Test1", outputZero.getFirstName());
        Assert.assertEquals("Last Name Test1", outputZero.getLastName());
        Assert.assertEquals("Address Test1", outputZero.getAddress());
        Assert.assertEquals("Female", outputZero.getGender());
        
        PersonVO outputSeven = outputList.get(7);
        
        Assert.assertEquals(Long.valueOf(8L), outputSeven.getKey());
        Assert.assertEquals("First Name Test8", outputSeven.getFirstName());
        Assert.assertEquals("Last Name Test8", outputSeven.getLastName());
        Assert.assertEquals("Address Test8", outputSeven.getAddress());
        Assert.assertEquals("Male", outputSeven.getGender());
        
        PersonVO outputTwelve = outputList.get(12);
        
        Assert.assertEquals(Long.valueOf(13L), outputTwelve.getKey());
        Assert.assertEquals("First Name Test13", outputTwelve.getFirstName());
        Assert.assertEquals("Last Name Test13", outputTwelve.getLastName());
        Assert.assertEquals("Address Test13", outputTwelve.getAddress());
        Assert.assertEquals("Female", outputTwelve.getGender());
    }

    @Test
    public void parseVOToEntityTest() {
        Person output = DozerConverter.parseObject(inputObject.mockEntityVO(), Person.class);
        Assert.assertEquals(Long.valueOf(1L), output.getId());
        Assert.assertEquals("First Name Test1", output.getFirstName());
        Assert.assertEquals("Last Name Test1", output.getLastName());
        Assert.assertEquals("Address Test1", output.getAddress());
        Assert.assertEquals("Female", output.getGender());
    }

    @Test
    public void parserVOListToEntityListTest() {
        List<Person> outputList = DozerConverter.parseListObjects(inputObject.mockEntityListVO(), Person.class);
        Person outputZero = outputList.get(0);
        
        Assert.assertEquals(Long.valueOf(1L), outputZero.getId());
        Assert.assertEquals("First Name Test1", outputZero.getFirstName());
        Assert.assertEquals("Last Name Test1", outputZero.getLastName());
        Assert.assertEquals("Address Test1", outputZero.getAddress());
        Assert.assertEquals("Female", outputZero.getGender());
        
        Person outputSeven = outputList.get(7);
        
        Assert.assertEquals(Long.valueOf(8L), outputSeven.getId());
        Assert.assertEquals("First Name Test8", outputSeven.getFirstName());
        Assert.assertEquals("Last Name Test8", outputSeven.getLastName());
        Assert.assertEquals("Address Test8", outputSeven.getAddress());
        Assert.assertEquals("Male", outputSeven.getGender());
        
        Person outputTwelve = outputList.get(12);
        
        Assert.assertEquals(Long.valueOf(13L), outputTwelve.getId());
        Assert.assertEquals("First Name Test13", outputTwelve.getFirstName());
        Assert.assertEquals("Last Name Test13", outputTwelve.getLastName());
        Assert.assertEquals("Address Test13", outputTwelve.getAddress());
        Assert.assertEquals("Female", outputTwelve.getGender());
    }
}