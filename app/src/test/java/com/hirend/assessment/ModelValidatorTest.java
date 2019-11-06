package com.hirend.assessment;

import com.hirend.assessment.model.dto.response.InfoResponse;

import org.junit.Test;

/**
 * Created by HirenD on 28/10/19.
 */

public class ModelValidatorTest {
    @Test()
    public void shouldNotThrowErrorOnValidCharacterModel() throws IllegalArgumentException {
        InfoResponse builder = new InfoResponse(null, null);
        builder.setTitle("Testing");

    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowErrorOnInvalidCharacterModel() throws IllegalArgumentException {
    }
}