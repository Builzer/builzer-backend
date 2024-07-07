package com.builzer.backend.project.adapter.out.persistence

import com.builzer.backend.project.adapter.out.persistence.entity.enums.DatabaseType
import com.builzer.backend.project.adapter.out.persistence.repository.SupportedDatabaseJpaRepository
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.TestConstructor

@ActiveProfiles("test")
@DataJpaTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class SupportedDatabaseJpaRepositoryTest(private val supportedDatabaseJpaRepository: SupportedDatabaseJpaRepository)
    : BehaviorSpec({

    Given("db종류, db버전으로") {
        val databaseType = DatabaseType.MYSQL
        val databaseVersion = 8.0F

        When("find를 하면") {
            val supportedLanguage = supportedDatabaseJpaRepository
                    .find(databaseType = databaseType, databaseVersion = databaseVersion)!!

            Then("해당하는 지원 DB를 가져올 수 있다.") {
                supportedLanguage.id shouldNotBe null
                supportedLanguage.databaseType shouldBe databaseType
                supportedLanguage.databaseVersion shouldBe databaseVersion
            }
        }
    }
})