package com.microservices.CompanyMs.company;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String address;

//    @JsonIgnore
//    @OneToMany(mappedBy = "company", fetch=FetchType.EAGER)
//    private List<Job> jobs;
//
//    @JsonIgnore
//    @OneToMany(mappedBy = "company", fetch=FetchType.LAZY)   //Hibernate Default is LAZY loading - in both OneToMany and ManyToOne.
//    private List<Review> reviews;

}

/*
    Where to Place @JsonIgnore:
On @OneToMany:
Typically, you place @JsonIgnore on the @OneToMany side because this is the side holding the list of child entities,
 and you usually don't want to serialize the entire list.
On @ManyToOne (if necessary):
Sometimes, if you're serializing the child entity and don't want the parent entity to be included (for example,
to avoid circular references or large payloads), you can also add @JsonIgnore on the @ManyToOne side. However, this is less common.

 */

/*How the Job and Company Differ in Insertion:
        Company First, then Job:

When inserting a Company first, you're creating a record with a unique id in the Company table.
        After that, when you insert a Job, you can reference the Company using its id (company_id in the Job table), ensuring the foreign key constraint is satisfied.
The Company acts as the parent in the relationship, while the Job acts as the child.
Job First (Why it Fails):

If you try to insert a Job without the corresponding Company existing in the database, the foreign key constraint would prevent the insertion because the company_id points to a non-existing company.
This is because a Job cannot be linked to a Company that does not yet exist.
Bidirectional Relationships (Company Referencing Jobs):
The @OneToMany relationship on the Company side (list of jobs) is inverse and mapped by the Job entity.
In other words, it is not the owning side of the relationship. It doesn't affect the foreign key in the database. It simply means that when you retrieve a Company, you will also get a list of its associated Jobs (but this does not affect how data is inserted).
The owning side is still the Job entity, as it holds the company_id foreign key.
What Happens Internally:
When you insert a Company, nothing is added to the Job table at that point. The Job table will only be affected when you add Job entries that reference a valid company_id.
The list of jobs in the Company is populated only when data is fetched from the database, not when the data is inserted.
Summary:
Insert Company first: The Company provides the id required by the Job's foreign key (company_id), ensuring the relationship is maintained correctly.
Foreign key constraint: Inserting a Job without a corresponding Company will fail because the foreign key reference (company_id) needs to point to an existing Company.
Bidirectional nature: The @OneToMany on the Company side doesn’t control the database mapping—it’s there for fetching and representing the relationship in the code but doesn’t affect the insertion process
*/