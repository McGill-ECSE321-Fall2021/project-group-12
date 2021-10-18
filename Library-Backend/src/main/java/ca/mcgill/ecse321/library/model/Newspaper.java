package ca.mcgill.ecse321.library.model;

import javax.persistence.*;

@Entity
public class Newspaper extends Item
{

  public void delete()
  {
    super.delete();
  }

}
