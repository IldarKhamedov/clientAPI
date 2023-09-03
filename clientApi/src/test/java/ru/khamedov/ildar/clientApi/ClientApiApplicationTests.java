package ru.khamedov.ildar.clientApi;


import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.khamedov.ildar.clientApi.dto.ContactDTO;
import ru.khamedov.ildar.clientApi.model.Client;
import ru.khamedov.ildar.clientApi.model.Contact;
import ru.khamedov.ildar.clientApi.model.ContactType;
import ru.khamedov.ildar.clientApi.model.EmailContact;
import ru.khamedov.ildar.clientApi.repository.ContactRepository;
import ru.khamedov.ildar.clientApi.repository.UserProfileRepository;
import ru.khamedov.ildar.clientApi.service.ModelMapperService;

@SpringBootTest
class ClientApiApplicationTests {

	@Resource
	private UserProfileRepository userProfileRepository;

	@Resource
	private ContactRepository contactRepository;

	@Resource
	private ModelMapperService modelMapperService;

	private static final String NAME="C9MeOf6ZJYDqo4EFcTAScb4SuTkcl2iXHEYL49ib7syeu6oO9g";
	private static final String PASSWORD="123456";
	private static final String CONTACT="C9MeOf6ZJYDqo4EFcTAScb4SuTkcl2iXHEYL49ib7syeu6oO9g@mail.ru";


	@Test
	@Transactional
	void checkClient() {
		Client client=createClient();
		Client baseClient=(Client) userProfileRepository.findByName(NAME);
		Assertions.assertEquals(client,baseClient);
		userProfileRepository.delete(baseClient);
	}

	@Test
	void checkContact() {
		Contact contact=createContact();
		ContactDTO contactDTO= modelMapperService.convertToContactDTO(contact);
		Assertions.assertEquals(contactDTO.getContactType(), ContactType.EMAIL);
	}

	private Client createClient(){
		Client client=new Client();
		client.setName(NAME);
		client.setPassword(PASSWORD);
		userProfileRepository.save(client);
		return client;
	}

	private Contact createContact(){
		Contact contact=new EmailContact();
		contact.setInformation(CONTACT);
		return contact;
	}

}
