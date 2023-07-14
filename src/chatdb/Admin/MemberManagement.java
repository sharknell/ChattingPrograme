package chatdb.Admin;

import java.util.ArrayList;

import chatdb.ChatDAO;
import chatdb.MemberDTO;

public class MemberManagement extends MemberDTO {
	public MemberManagement() {
	 ChatDAO dao = ChatDAO.getInstance();
	 ArrayList<MemberDTO> members = dao.getAllMember();

     for (MemberDTO dto : members) {
         System.out.println(dto.getName() + " " +
                 dto.getId() + " " +
                 dto.getPassword() + " " +
                 dto.getPhonenumber() + " " +
                 dto.getReg_date() + " " +
                 dto.getRRN() + " " +
                 dto.getCountrycode());
     }
}
}
