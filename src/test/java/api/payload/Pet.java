package api.payload;

import java.util.List;

import api.payloadManager.Category;
import api.payloadManager.Tag;

public class Pet 
{
	private String id;
	private Category category;
	private String name;
	private List<String> photoUrls;
	private List<Tag> tags;
	private String status;
	

	

	// Getters and Setters
	public String getId() 
	{
		return id;
	}

	public void setId(String petId) 
	{
		this.id = petId;
	}

	public Category getCategory() 
	{
		return category;
	}

	public void setCategory(Category category) 
	{
		this.category = category;
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public List<String> getPhotoUrls() 
	{
		return photoUrls;
	}

	public void setPhotoUrls(List<String> photoUrls) 
	{
		this.photoUrls = photoUrls;
	}

	public List<Tag> getTags() 
	{
		return tags;
	}

	public void setTags(List<Tag> tags) 
	{
		this.tags = tags;
	}

	public String getStatus() 
	{
		return status;
	}

	public void setStatus(String status) 
	{
		this.status = status;
	}

	
	
	

}
