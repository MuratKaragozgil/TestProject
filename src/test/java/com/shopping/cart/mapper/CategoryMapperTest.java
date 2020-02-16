package com.shopping.cart.mapper;

import com.shopping.cart.model.Category;
import com.shopping.cart.model.dto.CategoryDTO;
import com.shopping.cart.repository.CategoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author Murat Karagözgil
 */
@RunWith(MockitoJUnitRunner.class)
public class CategoryMapperTest {

    @InjectMocks
    private CategoryMapper categoryMapper;

    @Mock
    private CategoryRepository categoryRepository;

    private Category category;
    private CategoryDTO categoryDTO;

    @Before
    public void setup() {
        category = Category.builder().id(1L).title("category").build();
        categoryDTO = CategoryDTO.builder().id(1L).title("category").build();
    }

    @Test
    public void shouldToEntityReturnNull() {
        Category resultEntity = categoryMapper.convertToEntity(null);
        assertThat(resultEntity, equalTo(null));
    }

    @Test
    public void shouldToEntityHasId() {
        Mockito.when(categoryRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(category));

        Category resultEntity = categoryMapper.convertToEntity(categoryDTO);
        assertThat(resultEntity.getId(), equalTo(category.getId()));
        assertThat(resultEntity.getTitle(), equalTo(category.getTitle()));
        assertThat(resultEntity.getParent(), equalTo(category.getParent()));
    }

    @Test
    public void shouldToEntityNotFoundCategory() {
        Mockito.when(categoryRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        Category resultEntity = categoryMapper.convertToEntity(categoryDTO);
        assertThat(resultEntity.getId(), equalTo(null));
    }

    @Test
    public void shouldToEntityNotNewCategory() {
        categoryDTO.setId(null);

        Category resultEntity = categoryMapper.convertToEntity(categoryDTO);
        assertThat(resultEntity.getId(), equalTo(null));
    }

    @Test
    public void shouldToEntityHasParent() {
        Category parent = Category.builder().id(2L).title("parentCategory").build();
        categoryDTO.setParentCategoryId(parent.getId());

        Mockito.when(categoryRepository.findById(category.getId())).thenReturn(Optional.ofNullable(category));
        Mockito.when(categoryRepository.findById(parent.getId())).thenReturn(Optional.ofNullable(parent));

        Category resultEntity = categoryMapper.convertToEntity(categoryDTO);
        assertThat(resultEntity.getParent(), equalTo(parent));
    }

    @Test
    public void shouldToDtoReturnNull() {
        CategoryDTO resultDTO = categoryMapper.convertToDTO(null);
        assertThat(resultDTO, equalTo(null));
    }

    @Test
    public void shouldToDto() {
        CategoryDTO resultDTO = categoryMapper.convertToDTO(category);

        assertThat(resultDTO.getId(), equalTo(categoryDTO.getId()));
        assertThat(resultDTO.getTitle(), equalTo(categoryDTO.getTitle()));
        assertThat(resultDTO.getParentCategoryId(), equalTo(categoryDTO.getParentCategoryId()));
    }

    @Test
    public void shouldToDtoHasParent() {
        Category parent = Category.builder().id(2L).title("parentCategory").build();
        category.setParent(parent);

        CategoryDTO resultDTO = categoryMapper.convertToDTO(category);

        assertThat(resultDTO.getId(), equalTo(categoryDTO.getId()));
        assertThat(resultDTO.getTitle(), equalTo(categoryDTO.getTitle()));
        assertThat(resultDTO.getParentCategoryId(), equalTo(parent.getId()));
    }
}