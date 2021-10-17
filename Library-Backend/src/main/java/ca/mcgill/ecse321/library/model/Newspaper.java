package ca.mcgill.ecse321.library.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/


import java.sql.Date;

// line 37 "library.ump"
import javax.persistence.*;

@Entity
public class Newspaper extends Item
{



  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long newspaperId;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  //------------------------
  // INTERFACE
  //------------------------

  public void delete()
  {
    super.delete();
  }

  public Long getNewspaperId() {
      return this.newspaperId;
  }

  public void setNewspaperId(Long newspaperId) {
      this.newspaperId = newspaperId;
  }

}
