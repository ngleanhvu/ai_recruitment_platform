from pydantic import BaseModel, Field


class ProfileSchema(BaseModel):
    first_name: str | None = None
    last_name: str | None = None
    phone: str | None = None


class SkillSchema(BaseModel):
    name: str
    level: str | None = None
    years_of_experience: float | None = None


class ExperienceSchema(BaseModel):
    company: str
    position: str | None = None
    description: str | None = None
    start_date: str | None = None
    end_date: str | None = None
    is_current: bool = False


class EducationSchema(BaseModel):
    school: str
    major: str | None = None
    degree: str | None = None
    gpa: float | None = None
    start_year: int | None = None
    end_year: int | None = None


class SocialLinkSchema(BaseModel):
    type: str
    url: str


class AddressSchema(BaseModel):
    city: str | None = None
    country: str | None = None
    address: str | None = None
    district: str | None = None


class ExtractedCandidateSchema(BaseModel):
    profile: ProfileSchema
    email: str | None = None
    address: AddressSchema | None = None
    summary: str | None = None

    skills: list[SkillSchema] = Field(default_factory=list)
    experiences: list[ExperienceSchema] = Field(default_factory=list)
    educations: list[EducationSchema] = Field(default_factory=list)
    social_links: list[SocialLinkSchema] = Field(default_factory=list)
