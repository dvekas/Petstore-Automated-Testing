package org.dvekas.model.factories.pet;
import lombok.NoArgsConstructor;
import org.dvekas.model.pet.Category;
import org.dvekas.model.pet.Pet;
import org.dvekas.model.pet.PetStatusEnum;
import org.dvekas.model.pet.Tag;

import java.util.List;

@NoArgsConstructor
public class PetBuilder {

    String id;
    Category category;
    String name;
    List<String> photoUrls;
    List<Tag> tags;
    PetStatusEnum status;

    public Pet build() {
        Pet pet = new Pet();
            pet.setId(id);
            pet.setCategory(category);
            pet.setName(name);
            pet.setPhotoUrls(photoUrls);
            pet.setTags(tags);
            pet.setStatus(status);

        return pet;
    }

    public PetBuilder id(String id) {
        this.id = id;
        return this;
    }

    public PetBuilder Category(Category category) {
        this.category = category;
        return this;
    }

    public PetBuilder name(String name) {
        this.name = name;
        return this;
    }

    public PetBuilder photoUrls(List<String> photoUrls) {
        this.photoUrls = photoUrls;
        return this;
    }

    public PetBuilder tags(List<Tag> tags) {
        this.tags = tags;
        return this;
    }

    public PetBuilder petStatus(PetStatusEnum petStatus) {
        this.status = petStatus;
        return this;
    }
}
