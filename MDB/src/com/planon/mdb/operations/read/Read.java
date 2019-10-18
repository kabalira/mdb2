package com.planon.mdb.operations.read;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.planon.mdb.dto.ConfluenceDTO;
import com.planon.mdb.util.ConnectionUtil;

public class Read {

	public void readmdb() throws SQLException {

		List<ConfluenceDTO> list = new ArrayList<ConfluenceDTO>();

		try (Connection aConnection = ConnectionUtil.openConnection()) {

			String aSql = "SELECT * FROM RequirementInfo WHERE Type IN ('BO Specification (BO)','Entry Specification (ES)','General concepts (GC)','Requirement Specification (RS)') ";

			PreparedStatement aPreparedStatement = aConnection.prepareStatement(aSql);
			ResultSet aResult = aPreparedStatement.executeQuery();

			while (aResult.next()) {

				String aTag = aResult.getString("tag");
//				int aId_number = aResult.getInt("id_number");
				String aName = aResult.getString("Name");
				String aDescription = aResult.getString("Description");
				String aVersion = aResult.getString("version");
				String aHierarchy = aResult.getString("hierarchy");
				String aType = aResult.getString("Type");
//				String aOwner = aResult.getString("Owner");
//				String aStatus = aResult.getString("Status");
//				String aPriority = aResult.getString("Priority");
//				String aValidation = aResult.getString("Validation");

				if (!aHierarchy.startsWith("1."))// && !aHierarchy.equals("1")
					continue;
				// starts with 1
//				int aIndex = aResult.getInt("index");
//				String aName_tag = aResult.getString("name_tag");
//				System.out.println(
//						aTag + ", " + aName + ", " + aDescription + ", " + aVersion + ", " + aHierarchy + ", " + aType);
				ConfluenceDTO aConfluenceDTO = new ConfluenceDTO(aTag, aName, aDescription, aVersion, aHierarchy,
						aType);
				list.add(aConfluenceDTO);
//				if (list.size()==10) { //first 10 records
//					break;
//				}
			}
			Collections.sort(list, new Comparator<ConfluenceDTO>() {

				@Override
				public int compare(ConfluenceDTO aFirstPriority, ConfluenceDTO aSecondPriority) {

					String[] components1 = aFirstPriority.getHierarchy().split("\\.");
					String[] components2 = aSecondPriority.getHierarchy().split("\\.");

					// to solve 1.10, 1.2 issue, regular sort will return 1.2 then 1.10, but 1.10
					// should be the first element in the list
					int minLength = Math.min(components1.length, components2.length);

					for (int i = 0; i < minLength; i++) {
						int result = new Integer(components1[i]).compareTo(Integer.parseInt(components2[i]));
						if (result != 0) {
							return result;
						}
					}
					return Integer.compare(components1.length, components2.length);

				}
			});

			System.out.println("Starting");

			List<ConfluenceDTO> subList = list.subList(0, 10);

			for (ConfluenceDTO confluenceDTO : subList) {

				// 1. Create page
//				int id = createPage(confluenceDTO);{
//					sysout( DTO details)//					
//					return getPageId();}

				String id = confluenceDTO.getHierarchy().substring(0, (confluenceDTO.getHierarchy().length() - 2));
				String last = confluenceDTO.getHierarchy().substring(confluenceDTO.getHierarchy().length() - 1);
				System.out.println(confluenceDTO.getHierarchy() + " : Parent : " + id + " : last : " + last);

			}

			System.out.println("List lenght " + list.size());
		}
	}

//	private int getPageId() {
//		return (int) Math.random();
//	}

	public void traceFrom() throws SQLException {

		try (Connection aConnection = ConnectionUtil.openConnection()) {

			String aSql = "SELECT TraceFrom.from_id_number AS [From], RequirementInfo.id_number AS [To], RequirementInfo.Name AS To_name, RequirementInfo.tag AS To_tag, RequirementInfo.hierarchy\r\n"
					+ "FROM RequirementInfo INNER JOIN TraceFrom ON RequirementInfo.id_number = TraceFrom.id_number AND RequirementInfo.hierarchy = '1.10.3.2.3'";

			PreparedStatement aPreparedStatement = aConnection.prepareStatement(aSql);
//			aPreparedStatement.setString(1, "1.10.3.2.3");
			ResultSet aResult = aPreparedStatement.executeQuery();

			while (aResult.next()) {
				String id = aResult.getString(1);
				String id2 = aResult.getString(2);
				String name = aResult.getString(3);
				String tag_name = aResult.getString(4);
				String heirarchy = aResult.getString(5);
				if (!heirarchy.startsWith("1.") && !heirarchy.equals("1"))
					continue;

				System.out.println(id + " : : " + id2 + " : : " + name + " : : " + tag_name + " : : " + heirarchy);//
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void traceTo() throws SQLException {

		try (Connection aConnection = ConnectionUtil.openConnection()) {// 1.10.3.2.3

			String sql1 = "SELECT R3.hierarchy,R3.Name,R3.tag FROM RequirementInfo R3 INNER JOIN(SELECT TraceTo.to_id_number FROM TraceTo INNER JOIN RequirementInfo ON TraceTo.id_number = RequirementInfo.id_number WHERE RequirementInfo.hierarchy = '1.10.3.2.3')R2 ON R3.id_number = R2.to_id_number\r\n"
					+ "";

//			String aSql = "SELECT TraceTo.to_id_number AS [To] FROM TraceTo INNER JOIN RequirementInfo ON TraceTo.id_number = RequirementInfo.id_number";

			PreparedStatement aPreparedStatement = aConnection.prepareStatement(sql1);
			ResultSet aResult = aPreparedStatement.executeQuery();

			while (aResult.next()) {

				String name = aResult.getString(1);
				String tag_name = aResult.getString(2);
				String heirarchy = aResult.getString(3);

				if (!heirarchy.startsWith("1.") && !heirarchy.equals("1"))
					continue;
				System.out.println(name + " " + tag_name + " " + heirarchy);
			}
		}
	}
}
